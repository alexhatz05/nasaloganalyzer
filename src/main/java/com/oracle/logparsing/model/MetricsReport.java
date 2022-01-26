package com.oracle.logparsing.model;

import com.oracle.logparsing.util.ConsoleColors;

public class MetricsReport {

    private NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();

    private static final String SUCCESSFUL_REQ_PER_MESSAGE = "Percentage of successful requests: ";
    private static final String UNSUCCESSFUL_REQ_PER_MESSAGE = "Percentage of unsuccessful requests: ";

    public void produceMetricsReport() {
        /* Percentage of successful requests*/
        System.out.printf(SUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                nasaLogMetricsSingleton.computePercentage(true));
        /* Percentage of unsuccessful requests*/
        System.out.printf(UNSUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                nasaLogMetricsSingleton.computePercentage(false));
    }

    public void printReportsTitle(){
        System.out.println("\n"+
                ConsoleColors.CYAN_BOLD +
                "********************" + "\n" +
                "*** Final Report ***" + "\n" +
                "********************" +
                ConsoleColors.RESET_COLOUR);
    }
}

package com.oracle.logparsing.model;

import com.oracle.logparsing.util.ConsoleColors;

public class MetricsReport {

    private static final String SUCCESSFUL_REQ_PER_MESSAGE = "Percentage of successful requests: ";
    private static final String UNSUCCESSFUL_REQ_PER_MESSAGE = "Percentage of unsuccessful requests: ";
    private NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

    public void produceMetricsReport() {
        boolean isFlagProvided = metricsSingleton.isFlagOptionEnabled();

        /* Percentage of successful requests*/
        if (!isFlagProvided || (isFlagProvided && metricsSingleton.isSuccessPercentFlag()))
            System.out.printf(SUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                    metricsSingleton.computePercentage(true));

        /* Percentage of unsuccessful requests*/
        if (!isFlagProvided || isFlagProvided & metricsSingleton.isUnsuccessfulPercentFlag())
            System.out.printf(UNSUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                    metricsSingleton.computePercentage(false));
    }

    public void printReportsTitle() {
        System.out.println("\n" + ConsoleColors.CYAN_BOLD +
                        "********************" + "\n" +
                        "*** Final Report ***" + "\n" +
                        "********************"
                        + ConsoleColors.RESET_COLOUR);
    }
}

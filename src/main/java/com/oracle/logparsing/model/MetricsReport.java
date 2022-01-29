package com.oracle.logparsing.model;

import com.oracle.logparsing.util.ConsoleColors;

public class MetricsReport {

    private static final String SUCCESSFUL_REQ_PER_MESSAGE = "Percentage of successful requests: ";
    private static final String UNSUCCESSFUL_REQ_PER_MESSAGE = "Percentage of unsuccessful requests: ";
    private final NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

    public void produceMetricsReport() {
        boolean isFlagProvided = metricsSingleton.isFlagOptionEnabled();

        /* Percentage of successful requests*/
        if (!isFlagProvided || metricsSingleton.isSuccessPercentFlag())
            System.out.printf(SUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                    metricsSingleton.computePercentage(true));

        /* Percentage of unsuccessful requests*/
        if (!isFlagProvided || metricsSingleton.isUnsuccessfulPercentFlag())
            System.out.printf(UNSUCCESSFUL_REQ_PER_MESSAGE + "%.4f%%\n",
                    metricsSingleton.computePercentage(false));

        /* Top requested pages */
        if (!isFlagProvided || metricsSingleton.isTopRequestedFlag()) {
            printLineSeparator();
            printTopRequestedTitle();
            metricsSingleton.findTopRequestedPagesFromHashMap();
            printLineSeparator();
        }

        /* Top Unsuccessful */
        if (!isFlagProvided || metricsSingleton.isTopUnsuccessfulFlag()) {
            printLineSeparator();
            printTopUnsuccessfulTitle();
            metricsSingleton.findTopUnsuccessfulPagesFromHashMap();
            printLineSeparator();
        }

        /* Top Hosts */
        if(!isFlagProvided || metricsSingleton.isTopHostsFlag()) {
            printLineSeparator();
            printTopHostsTitle();
            metricsSingleton.findTopTenHosts();
            printLineSeparator();
        }
    }

    public void printReportsTitle() {
        System.out.println("\n" +
                        ConsoleColors.CYAN_BOLD +
                        "********************" + "\n" +
                        "*** Final Report ***" + "\n" +
                        "********************"
                        + ConsoleColors.RESET_COLOUR);
    }

    public void printLineSeparator() {
        System.out.println(ConsoleColors.YELLOW_COLOUR +
                "**********************************************************************" +
                ConsoleColors.RESET_COLOUR);
    }

    public void printTopRequestedTitle() {
        System.out.println(ConsoleColors.YELLOW_BOLD +
                "Top 10 Requested Pages************************************************" +
                ConsoleColors.RESET_COLOUR);
        printLineSeparator();
    }

    public void printTopUnsuccessfulTitle() {
        System.out.println(ConsoleColors.YELLOW_BOLD +
                "Top 10 Unsuccessful Page Requests*************************************" +
                ConsoleColors.RESET_COLOUR);
        printLineSeparator();
    }

    public void printTopHostsTitle() {
        System.out.println(ConsoleColors.YELLOW_BOLD +
                "Top 10 Hosts**********************************************************" +
                ConsoleColors.RESET_COLOUR);
        printLineSeparator();
    }
}

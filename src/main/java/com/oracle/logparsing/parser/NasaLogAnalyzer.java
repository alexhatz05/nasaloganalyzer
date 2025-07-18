package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.MetricsReport;
import com.oracle.logparsing.model.NasaLogMetricsSingleton;
import com.oracle.logparsing.util.ConsoleColors;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class NasaLogAnalyzer {

    public static void main(String[] args) {
        /* Validate provided arguments*/
        if (!validateInput(args))
            System.exit(0);

        /* If flag is provided */
        if (args.length == 2)
            updateFlagOptionParams(args[1]);

        if (!NasaLogFileParser.isValidFile(Paths.get(args[0]))) {
            System.out.println("Wrong log file path provided!");
            System.exit(0);
        }

        /*Parse the file and compute the metrics*/
        NasaLogFileParser fileParser = new NasaLogFileParser();
        try (FileInputStream fileInputStream = new FileInputStream(args[0]);
            Scanner scanner =new Scanner( fileInputStream, "UTF-8")) {
            fileParser.parseLogFile(scanner);

            /*Create the final report*/
            MetricsReport metricsReport = new MetricsReport();
            metricsReport.printReportsTitle();
            metricsReport.produceMetricsReport();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateFlagOptionParams(String option) {
        NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();
        nasaLogMetricsSingleton.setFlagOptionEnabled(true);

        switch (option) {
            case "topRequested":
                nasaLogMetricsSingleton.setTopRequestedFlag(true);
                break;
            case "successPercent":
                nasaLogMetricsSingleton.setSuccessPercentFlag(true);
                break;
            case "unsuccessfulPercent":
                nasaLogMetricsSingleton.setUnsuccessfulPercentFlag(true);
                break;
            case "topUnsuccessful":
                nasaLogMetricsSingleton.setTopUnsuccessfulFlag(true);
                break;
            case "topHosts":
                nasaLogMetricsSingleton.setTopHostsFlag(true);
                break;
            default:
                System.out.println("Unexpected value of parameter");
        }
    }

    public static boolean validateInput(String[] args) {
        boolean errorFree = true;

        if (args.length == 0) {
            System.out.println(ConsoleColors.YELLOW_COLOUR + "Please, provide the log file path!" + ConsoleColors.RESET_COLOUR);
            errorFree = false;
        }
        else if (args.length == 2) {
            if (!(args[1].equals("topRequested") || args[1].equals("successPercent") || args[1].equals("unsuccessfulPercent")
                    || args[1].equals("topUnsuccessful") || args[1].equals("topHosts"))) {
                System.out.println(ConsoleColors.YELLOW_COLOUR + "Please, provide a correct option!" + ConsoleColors.RESET_COLOUR);
                errorFree = false;
            }
        }
        else if (args.length > 2) {
            System.out.println(ConsoleColors.YELLOW_COLOUR + "Please, provide correct number of arguments!" + ConsoleColors.RESET_COLOUR);
            errorFree = false;
        }

        return errorFree;
    }
}

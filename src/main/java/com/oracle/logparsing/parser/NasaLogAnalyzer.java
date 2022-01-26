package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.MetricsReport;
import com.oracle.logparsing.model.NasaLogMetricsSingleton;
import com.oracle.logparsing.util.ConsoleColors;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogAnalyzer {

    public static void main(String[] args) {
        NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();

        if (args.length == 0) {
            System.out.println(ConsoleColors.YELLOW_COLOUR + "Please, provide the log file path!" +
                    ConsoleColors.RESET_COLOUR);
            return;
        }

        String filepath = args[0];

        try (Scanner scanner = NasaLogFileParser.loadLogFile(filepath)) {
            /*Parse the file and compute the metrics*/
            NasaLogFileParser fileParser = new NasaLogFileParser();
            fileParser.parseLogFile(scanner);

            /*Create the final report*/
            MetricsReport metricsReport = new MetricsReport();
            metricsReport.printReportsTitle();
            metricsReport.produceMetricsReport();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

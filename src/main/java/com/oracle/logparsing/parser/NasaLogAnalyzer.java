package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.MetricsReport;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogAnalyzer {

    public static void main(String args[]) {
        try (Scanner scanner = NasaLogFileParser.loadLogFile()) {
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

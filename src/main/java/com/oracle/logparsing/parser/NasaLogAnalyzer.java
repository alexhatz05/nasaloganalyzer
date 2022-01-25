package com.oracle.logparsing.parser;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogAnalyzer {

    public static void main(String args[]) {
        try (Scanner scanner = NasaLogFileParser.loadLogFile()) {
            NasaLogFileParser fileParser = new NasaLogFileParser();
            fileParser.parseLogFile(NasaLogFileParser.loadLogFile());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

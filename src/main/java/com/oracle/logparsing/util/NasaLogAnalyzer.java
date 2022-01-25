package com.oracle.logparsing.util;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogAnalyzer {

    public static void main(String args[]){

        try (Scanner scanner = NasaLogFileParser.loadLogFile()) {
            System.out.println("Processing");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

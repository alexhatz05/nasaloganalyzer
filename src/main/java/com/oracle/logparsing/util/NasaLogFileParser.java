package com.oracle.logparsing.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogFileParser {

    private int lineCounter = 0;

    public static Scanner loadLogFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/home/sword/Desktop/access_log_Aug95"));
        return scanner;
    }

    public void parseLogFile(Scanner scanner) {
        while(scanner.hasNextLine()){
            lineCounter++;
        }
    }
}

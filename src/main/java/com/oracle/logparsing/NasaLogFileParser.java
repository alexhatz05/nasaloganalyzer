package com.oracle.logparsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NasaLogFileParser {

    public static Scanner loadLogFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/home/sword/Desktop/access_log_Aug95"));
        return scanner;
    }

    public void parseLogFile(Scanner scanner) {
        while(scanner.hasNextLine()){

        }
    }
}

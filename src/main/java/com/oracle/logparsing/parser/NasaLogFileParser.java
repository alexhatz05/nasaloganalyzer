package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.LoggedRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaLogFileParser {

    private static final String RESET_COLOUR = "\u001B[0m";
    private static final String RED_COLOUR = "\u001B[31m";
    private int lineCounter = 0;

    public static Scanner loadLogFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/home/sword/Desktop/access_log_Aug95"));
        return scanner;
    }

    public void parseLogFile(Scanner scanner) {

        while (scanner.hasNextLine()) {
            lineCounter++;
            String loggedRequestPattern = "(\\S+\\b)(\\s-\\s-\\s)\\[(.*)\\]\\s\"([\\w]+\\s[\\S]+\\s[\\S]+)\"\\s(\\d{3})\\s(\\d+)";

            String line = scanner.nextLine();
            Pattern p = Pattern.compile(loggedRequestPattern);
            Matcher matcher = p.matcher(line);

            if (!matcher.matches()) {
                System.out.println(RED_COLOUR + "[Line " + lineCounter + "] ERROR! Malformed line" + RESET_COLOUR);
                continue;
            }

            String host = matcher.group(1);
            String request = matcher.group(4);
            int responseCode = Integer.parseInt(matcher.group(5));

            LoggedRequest loggedRequest = new LoggedRequest(host, request, responseCode);

        }
    }
}

package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.LoggedRequest;
import com.oracle.logparsing.model.NasaLogMetricsSingleton;
import com.oracle.logparsing.util.ConsoleColors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaLogFileParser {

    private int lineCounter = 0;

    public static final String LOG_FILE_LINE_REGEX = "(\\S+\\b)(\\s-\\s-\\s)\\[(.*)]\\s\"([\\w]+\\s[\\S]+\\s[\\S]+)\"\\s(\\d{3})\\s(\\d+)";
    public static final String LOG_FILE_404_LINES_REGEX = "(\\S+\\b)(\\s-\\s-\\s)\\[(.*)]\\s\"([\\w]+\\s[\\S]+\\s[\\S]+)\"\\s([4-9]\\d{2}|[1-9]\\d{3})\\s(-)";

    private final NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();

    public static boolean isValidFile(Path filepath) {
        return Files.exists(filepath);
    }

    public void parseLogFile(Scanner sc) {
        Pattern p = Pattern.compile(LOG_FILE_LINE_REGEX);
        Pattern pNotFound = Pattern.compile(LOG_FILE_404_LINES_REGEX);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lineCounter++;

            Matcher matcher = p.matcher(line);
            Matcher matcherNotFound = pNotFound.matcher(line);

            if (!matcher.matches() && !matcherNotFound.matches()) {
                System.out.println(ConsoleColors.RED_COLOUR + "[Line " + lineCounter + "] ERROR! Malformed line" +
                        ConsoleColors.RESET_COLOUR);
                continue;
            }

            String host = (matcher.matches()) ? matcher.group(1) : matcherNotFound.group(1);
            String request = (matcher.matches()) ? matcher.group(4) : matcherNotFound.group(4);
            int responseCode = (matcher.matches()) ? Integer.parseInt(matcher.group(5)) :
                    Integer.parseInt(matcherNotFound.group(5));

            LoggedRequest loggedRequest = new LoggedRequest(host, request, responseCode);
            updateMetrics(loggedRequest);
        }
    }

    public void updateMetrics(LoggedRequest loggedRequest) {
        nasaLogMetricsSingleton.increaseTotalRequests();

        if (loggedRequest.isSuccessful())
            nasaLogMetricsSingleton.increaseSuccessfulRequests();
        else
            nasaLogMetricsSingleton.increaseUnsuccessfulRequests();

        if (!loggedRequest.isSuccessful())
            nasaLogMetricsSingleton.addEntryInUnsuccessfulPages(loggedRequest.getRequestURL());

        nasaLogMetricsSingleton.addEntryInRequestedPages(loggedRequest.getRequestURL());
        nasaLogMetricsSingleton.addEntryInHosts(loggedRequest.getHost());
        nasaLogMetricsSingleton.addEntryInRequestObjs(loggedRequest);
    }
}

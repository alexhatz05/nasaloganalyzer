package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.LoggedRequest;
import com.oracle.logparsing.model.NasaLogMetricsSingleton;
import com.oracle.logparsing.util.ConsoleColors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaLogFileParser {

    private int lineCounter = 0;

    private NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();

    public static Path loadLogFile(String filepath) {
        Path filePath = Paths.get(filepath);
        return filePath;
    }

    public static String getLogFileLineRegex() {
        return "(\\S+\\b)(\\s-\\s-\\s)\\[(.*)\\]\\s\"([\\w]+\\s[\\S]+\\s[\\S]+)\"\\s(\\d{3})\\s(\\d+)";
    }

    public static String getLogFileLineRegexForNotFound() {
        return "(\\S+\\b)(\\s-\\s-\\s)\\[(.*)\\]\\s\"([\\w]+\\s[\\S]+\\s[\\S]+)\"\\s(\\d{3})\\s(-)";
    }

    public static boolean isValidFile(Path filepath) {
        return Files.exists(filepath) ? true : false;
    }

    public void parseLogFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.ISO_8859_1);
        for (String line : lines) {
            lineCounter++;

            Pattern p = Pattern.compile(getLogFileLineRegex());
            Matcher matcher = p.matcher(line);

            Pattern pNotFound = Pattern.compile(getLogFileLineRegexForNotFound());
            Matcher matcherNotFound = pNotFound.matcher(line);

            if (!matcher.matches() && !matcherNotFound.matches()) {
                System.out.println(
                        ConsoleColors.RED_COLOUR + "[Line " + lineCounter + "] ERROR! Malformed line" + ConsoleColors.RESET_COLOUR);
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

        nasaLogMetricsSingleton.addEntryInRequestedPages(loggedRequest.getRequestURL());

        if (!loggedRequest.isSuccessful())
            nasaLogMetricsSingleton.addEntryInUnsuccessfulPages(loggedRequest.getRequestURL());
    }
}

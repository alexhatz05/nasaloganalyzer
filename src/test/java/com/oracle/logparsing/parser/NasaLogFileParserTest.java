package com.oracle.logparsing.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class NasaLogFileParserTest {

    @Test
    void isValidFileTest() {
        Path filepath = Paths.get("dummy/path/log");
        Assertions.assertFalse(NasaLogFileParser.isValidFile(filepath));
    }

    @Test
    void givenCorrectlyFormattedLine_whenLogFileLineRegex_thenMatches() {
        String input = "in24.inetnebr.com - - [01/Aug/1995:00:00:01 -0400] \"GET /shuttle/missions/sts-68/news/sts-68-mcc-05.txt HTTP/1.0\" 200 1839";
        boolean matches = input.matches(NasaLogFileParser.LOG_FILE_LINE_REGEX);

        Assertions.assertTrue(matches);
    }

    @Test
    void givenMalformedLine_whenLogFileLineRegex_thenNoMatch() {
        String input = "google.com - - [01/Aug/1995:00:00:01 -0400] \"GET\" 500 -";
        boolean matches = input.matches(NasaLogFileParser.LOG_FILE_LINE_REGEX);

        Assertions.assertFalse(matches);
    }

    @Test
    void givenNotFoundRequestLine_whenLogFile404LinesRegex_thenMatches() {
        String input = "google.com - - [01/Aug/1995:00:00:01 -0400]  \"GET /shuttle/missions/sts-68/news/sts-68-mcc-05.txt HTTP/1.0\" 400 -";
        boolean matches = input.matches(NasaLogFileParser.LOG_FILE_404_LINES_REGEX);

        Assertions.assertFalse(matches);
    }

    @Test
    void givenMalformedLine_whenLogFile404LinesRegex_thenNoMatch() {
        String input = "google.com - - [01/Aug/1995:00:00:01 -0400] \"GET\" 500 -";
        boolean matches = input.matches(NasaLogFileParser.LOG_FILE_404_LINES_REGEX);
    }

}
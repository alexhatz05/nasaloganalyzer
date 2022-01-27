package com.oracle.logparsing.parser;

import com.oracle.logparsing.util.ConsoleColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class NasaLogAnalyzerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void validateInput_whenFilepathNotProvided_thenReturnError() {
        String[] args = new String[0];

        Assertions.assertEquals(false, NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide the log file path!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }

    @Test
    void validateInput_whenWrongNumberOfArgumentsProvided_thenReturnError() {
        String[] args = new String[3];
        args[0] = "/home/rheluser/Desktop/dummylogfile";
        args[1] = "topRequested";
        args[2] = "topHosts";

        Assertions.assertEquals(false, NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide correct number of arguments!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }

    @Test
    void validateInput_whenWrongFlagProvided_thenReturnError() {
        String[] args = new String[2];
        args[0] = "/home/rheluser/Desktop/dummylogfile";
        args[1] = "topDummy";

        Assertions.assertEquals(false, NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide a correct option!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }
}
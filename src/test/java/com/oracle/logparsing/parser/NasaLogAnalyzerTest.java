package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.NasaLogMetricsSingleton;
import com.oracle.logparsing.util.ConsoleColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class NasaLogAnalyzerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void givenFilepathNotProvided_whenValidateInput_thenErrorReturned() {
        String[] args = new String[0];

        Assertions.assertFalse(NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide the log file path!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }

    @Test
    void givenWrongNumberOfArgumentsProvided_whenValidateInput_thenErrorReturned() {
        String[] args = new String[3];
        args[0] = "/home/rheluser/Desktop/dummylogfile";
        args[1] = "topRequested";
        args[2] = "topHosts";

        Assertions.assertFalse(NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide correct number of arguments!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }

    @Test
    void givenWrongFlagProvided_whenValidateInput_thenErrorReturned() {
        String[] args = new String[2];
        args[0] = "/home/rheluser/Desktop/dummylogfile";
        args[1] = "topDummy";

        Assertions.assertFalse(NasaLogAnalyzer.validateInput(args));
        Assertions.assertEquals("Please, provide a correct option!",
                outputStreamCaptor.toString().
                        replace(ConsoleColors.YELLOW_COLOUR, "").
                        replace(ConsoleColors.RESET_COLOUR, "").
                        trim());
    }

    @Test
    void givenCorrectFlagProvided_whenValidateInput_thenNoErrorReturned() {
        String[] args = new String[2];
        args[0] = "/home/rheluser/Desktop/dummylogfile";

        args[1] = "topRequested";
        Assertions.assertTrue(NasaLogAnalyzer.validateInput(args));

        args[1] = "successPercent";
        Assertions.assertTrue(NasaLogAnalyzer.validateInput(args));

        args[1] = "unsuccessfulPercent";
        Assertions.assertTrue(NasaLogAnalyzer.validateInput(args));

        args[1] = "topUnsuccessful";
        Assertions.assertTrue(NasaLogAnalyzer.validateInput(args));

        args[1] = "topHosts";
        Assertions.assertTrue(NasaLogAnalyzer.validateInput(args));
    }

    @Test
    void updateFlagOptionParamsTet() {
        NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

        Assertions.assertFalse(metricsSingleton.isFlagOptionEnabled());
        Assertions.assertFalse(metricsSingleton.isTopRequestedFlag());
        Assertions.assertFalse(metricsSingleton.isSuccessPercentFlag());
        Assertions.assertFalse(metricsSingleton.isUnsuccessfulPercentFlag());
        Assertions.assertFalse(metricsSingleton.isTopUnsuccessfulFlag());
        Assertions.assertFalse(metricsSingleton.isTopHostsFlag());

        NasaLogAnalyzer.updateFlagOptionParams("topRequested");
        Assertions.assertTrue(metricsSingleton.isFlagOptionEnabled());
        Assertions.assertTrue(metricsSingleton.isTopRequestedFlag());
        Assertions.assertFalse(metricsSingleton.isSuccessPercentFlag());
        Assertions.assertFalse(metricsSingleton.isUnsuccessfulPercentFlag());
        Assertions.assertFalse(metricsSingleton.isTopUnsuccessfulFlag());
        Assertions.assertFalse(metricsSingleton.isTopHostsFlag());
    }
}
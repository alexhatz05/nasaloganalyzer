package com.oracle.logparsing.parser;

import com.oracle.logparsing.model.NasaLogMetricsSingleton;
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

    @Test
    void validateInput_whenCorrectFlagProvided_thenValidateInput() {
        String[] args = new String[2];
        args[0] = "/home/rheluser/Desktop/dummylogfile";

        args[1] = "topRequested";
        Assertions.assertEquals(true, NasaLogAnalyzer.validateInput(args));

        args[1] = "successPercent";
        Assertions.assertEquals(true, NasaLogAnalyzer.validateInput(args));

        args[1] = "unsuccessfulPercent";
        Assertions.assertEquals(true, NasaLogAnalyzer.validateInput(args));

        args[1] = "topUnsuccessful";
        Assertions.assertEquals(true, NasaLogAnalyzer.validateInput(args));

        args[1] = "topHosts";
        Assertions.assertEquals(true, NasaLogAnalyzer.validateInput(args));
    }

    @Test
    void updateFlagOptionParamsTet() {
        NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

        Assertions.assertEquals(false, metricsSingleton.isFlagOptionEnabled());
        Assertions.assertEquals(false, metricsSingleton.isTopRequestedFlag());
        Assertions.assertEquals(false, metricsSingleton.isSuccessPercentFlag());
        Assertions.assertEquals(false, metricsSingleton.isUnsuccessfulPercentFlag());
        Assertions.assertEquals(false, metricsSingleton.isTopUnsuccessfulFlag());
        Assertions.assertEquals(false, metricsSingleton.isTopHostsFlag());

        NasaLogAnalyzer.updateFlagOptionParams("topRequested");
        Assertions.assertEquals(true, metricsSingleton.isFlagOptionEnabled());
        Assertions.assertEquals(true, metricsSingleton.isTopRequestedFlag());
        Assertions.assertEquals(false, metricsSingleton.isSuccessPercentFlag());
        Assertions.assertEquals(false, metricsSingleton.isUnsuccessfulPercentFlag());
        Assertions.assertEquals(false, metricsSingleton.isTopUnsuccessfulFlag());
        Assertions.assertEquals(false, metricsSingleton.isTopHostsFlag());
    }
}
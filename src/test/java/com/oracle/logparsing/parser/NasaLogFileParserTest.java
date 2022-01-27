package com.oracle.logparsing.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class NasaLogFileParserTest {

    @Test
    void isValidFileTest() {
        Path filepath = Paths.get("dummy/path/log");
        Assertions.assertEquals(false, NasaLogFileParser.isValidFile(filepath));
    }

}
package com.oracle.logparsing.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoggedRequestTest {

    private LoggedRequest loggedRequest;

    @BeforeEach
    void init(){
        loggedRequest = new LoggedRequest("www.google.com", "GET /www.yahoo.com HTTP/1.0", 200);
    }

    @Test
    void givenSuccessfulRequestCode_whenIsSuccessful_thenReturnTrue() {
        Assertions.assertTrue(loggedRequest.isSuccessful());
        loggedRequest.setRequestCode(302);
        Assertions.assertTrue(loggedRequest.isSuccessful());
    }

    @Test
    void givenUnsuccessfulRequestCode_whenIsSuccessful_theReturnFalse() {
        loggedRequest.setRequestCode(400);
        Assertions.assertFalse(loggedRequest.isSuccessful());
        loggedRequest.setRequestCode(404);
        Assertions.assertFalse(loggedRequest.isSuccessful());
        loggedRequest.setRequestCode(500);
        Assertions.assertFalse(loggedRequest.isSuccessful());
    }
}
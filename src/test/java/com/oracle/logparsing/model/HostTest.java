package com.oracle.logparsing.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HostTest {

    private Host host;

    @BeforeEach
    void init() {
        NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();
        List<LoggedRequest> loggedRequestList = new ArrayList<>();
        host = new Host("www.google.com");

        LoggedRequest lr1 = new LoggedRequest("www.google.com","GET \"/www.page1.com\" HTTP/1.0", 200);
        LoggedRequest lr2 = new LoggedRequest("www.nasa.com", "GET \"/www.page2.com\" HTTP/1.0", 200);
        LoggedRequest lr3 = new LoggedRequest("www.di.uoa.gr", "GET \"/www.page3.com\" HTTP/1.0", 200);
        LoggedRequest lr4 = new LoggedRequest("www.google.com","GET \"/www.page4.com\" HTTP/1.0", 200);
        LoggedRequest lr5 = new LoggedRequest("www.nasa.com", "GET \"/www.page5.com\" HTTP/1.0", 200);
        LoggedRequest lr6 = new LoggedRequest("www.google.com", "GET \"/www.page6.com\" HTTP/1.0", 200);
        LoggedRequest lr7 = new LoggedRequest("www.google.com", "GET \"/www.page7.com\" HTTP/1.0", 200);
        LoggedRequest lr8 = new LoggedRequest("www.google.com","GET \"/www.page4.com\" HTTP/1.0", 200);

        loggedRequestList.add(lr1);
        loggedRequestList.add(lr2);
        loggedRequestList.add(lr3);
        loggedRequestList.add(lr4);
        loggedRequestList.add(lr5);
        loggedRequestList.add(lr6);
        loggedRequestList.add(lr7);
        loggedRequestList.add(lr8);
        nasaLogMetricsSingleton.setRequestObjs(loggedRequestList);
    }

    @Test
    void findRequestsOfHostTest() {
        Map<String, Long> expectedRequestsByHost = new HashMap<>();

        expectedRequestsByHost.put("\"/www.page4.com\"", 2L);
        expectedRequestsByHost.put("\"/www.page1.com\"", 1L);
        expectedRequestsByHost.put("\"/www.page6.com\"", 1L);
        expectedRequestsByHost.put("\"/www.page7.com\"", 1L);

        Assertions.assertEquals(expectedRequestsByHost, host.findRequestsOfHost());
    }
}
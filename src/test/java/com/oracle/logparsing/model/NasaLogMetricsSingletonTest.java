package com.oracle.logparsing.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NasaLogMetricsSingletonTest {

    private NasaLogMetricsSingleton nasaLogMetricsSingleton;

    @BeforeEach
    void init(){
        nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();
        nasaLogMetricsSingleton.setSuccessfulRequests(500);
        nasaLogMetricsSingleton.setUnsuccessfulRequests(100);
        nasaLogMetricsSingleton.setTotalRequests(600);
    }

    @Test
    void givenTrueParameter_whenComputePercentage_thenReturnSuccessPercentage() {
        Assertions.assertEquals(83.33333333333334,
                nasaLogMetricsSingleton.computePercentage(true));
    }

    @Test
    void givenFalseParameter_whenComputePercentage_thenReturnUnsuccessfulPercentage() {
        Assertions.assertEquals(16.666666666666664,
                nasaLogMetricsSingleton.computePercentage(false));
    }
}
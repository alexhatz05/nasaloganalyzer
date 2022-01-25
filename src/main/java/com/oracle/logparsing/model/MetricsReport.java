package com.oracle.logparsing.model;

public class MetricsReport {

    private NasaLogMetricsSingleton nasaLogMetricsSingleton = NasaLogMetricsSingleton.getInstance();

    private static final String SUCCESSFUL_REQ_PER_MESSAGE = "Percentage of successful requests: ";
    private static final String UNSUCCESSFUL_REQ_PER_MESSAGE = "Percentage of unsuccessful requests: ";

    public void produceMetricsReport() {
        
    }
}

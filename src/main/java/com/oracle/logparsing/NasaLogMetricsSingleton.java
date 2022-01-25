package com.oracle.logparsing;

import java.util.HashMap;

public class NasaLogMetricsSingleton {

    private static NasaLogMetricsSingleton nasaLogMetrics = new NasaLogMetricsSingleton();
    private HashMap<String, Integer> requestedPages = new HashMap<>();
    private HashMap<String, Integer> unsuccessfulReqPages = new HashMap<>();
    private int successfulRequests = 0;
    private int unsuccessfulRequests = 0;

    private NasaLogMetricsSingleton() {
    }

    public static NasaLogMetricsSingleton getInstance() {
        return nasaLogMetrics;
    }

    /*Getters and Setters*/

    public HashMap<String, Integer> getRequestedPages() {
        return requestedPages;
    }

    public void setRequestedPages(HashMap<String, Integer> requestedPages) {
        this.requestedPages = requestedPages;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public void setSuccessfulRequests(int successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public int getUnsuccessfulRequests() {
        return unsuccessfulRequests;
    }

    public void setUnsuccessfulRequests(int unsuccessfulRequests) {
        this.unsuccessfulRequests = unsuccessfulRequests;
    }

    public HashMap<String, Integer> getUnsuccessfulReqPages() {
        return unsuccessfulReqPages;
    }

    public void setUnsuccessfulReqPages(HashMap<String, Integer> unsuccessfulReqPages) {
        this.unsuccessfulReqPages = unsuccessfulReqPages;
    }

    /*Util*/

    public void addEntryInRequestedPages(String url) {
        requestedPages.put(url, requestedPages.containsKey(url) ?
                requestedPages.get(url) + 1 : 1);
    }

    public void addEntryInUnsuccessfulPages(String url) {
        unsuccessfulReqPages.put(url, unsuccessfulReqPages.containsKey(url) ?
                unsuccessfulReqPages.get(url) + 1 : 1);
    }

}

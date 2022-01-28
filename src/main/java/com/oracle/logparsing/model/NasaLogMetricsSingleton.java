package com.oracle.logparsing.model;

import java.util.HashMap;
import java.util.Map;

public class NasaLogMetricsSingleton {

    private static NasaLogMetricsSingleton nasaLogMetrics = new NasaLogMetricsSingleton();

    private HashMap<String, Integer> requestedPages = new HashMap<>();
    private HashMap<String, Integer> unsuccessfulReqPages = new HashMap<>();
    private int successfulRequests = 0;

    private int unsuccessfulRequests = 0;
    private int totalRequests = 0;

    private boolean flagOptionEnabled = false;
    private boolean topRequestedFlag = false;
    private boolean successPercentFlag = false;
    private boolean unsuccessfulPercentFlag = false;
    private boolean topUnsuccessfulFlag = false;
    private boolean topHostsFlag = false;

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

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public boolean isFlagOptionEnabled() {
        return flagOptionEnabled;
    }

    public void setFlagOptionEnabled(boolean flagOptionEnabled) {
        this.flagOptionEnabled = flagOptionEnabled;
    }

    public boolean isTopRequestedFlag() {
        return topRequestedFlag;
    }

    public void setTopRequestedFlag(boolean topRequestedFlag) {
        this.topRequestedFlag = topRequestedFlag;
    }

    public boolean isSuccessPercentFlag() {
        return successPercentFlag;
    }

    public void setSuccessPercentFlag(boolean successPercentFlag) {
        this.successPercentFlag = successPercentFlag;
    }

    public boolean isUnsuccessfulPercentFlag() {
        return unsuccessfulPercentFlag;
    }

    public void setUnsuccessfulPercentFlag(boolean unsuccessfulPercentFlag) {
        this.unsuccessfulPercentFlag = unsuccessfulPercentFlag;
    }

    public boolean isTopUnsuccessfulFlag() {
        return topUnsuccessfulFlag;
    }

    public void setTopUnsuccessfulFlag(boolean topUnsuccessfulFlag) {
        this.topUnsuccessfulFlag = topUnsuccessfulFlag;
    }

    public boolean isTopHostsFlag() {
        return topHostsFlag;
    }

    public void setTopHostsFlag(boolean topHostsFlag) {
        this.topHostsFlag = topHostsFlag;
    }

    /*Util*/

    public void increaseTotalRequests() {
        this.totalRequests++;
    }

    public void increaseSuccessfulRequests() {
        this.successfulRequests++;
    }

    public void increaseUnsuccessfulRequests() {
        this.unsuccessfulRequests++;
    }

    public void addEntryInRequestedPages(String url) {
        requestedPages.put(url, requestedPages.containsKey(url) ? requestedPages.get(url) + 1 : 1);
    }

    public void addEntryInUnsuccessfulPages(String url) {
        unsuccessfulReqPages.put(url, unsuccessfulReqPages.containsKey(url) ? unsuccessfulReqPages.get(url) + 1 : 1);
    }

    public double computePercentage(boolean successful) {
        int noOfRequests = (successful) ? this.successfulRequests : this.unsuccessfulRequests;
        return (((double) noOfRequests) / ((double) this.totalRequests)) * 100;
    }

    public void sortRequestedPagesHashMap() {
        requestedPages.entrySet().
                stream().
                sorted(Map.Entry.<String, Integer>comparingByValue().
                reversed()).
                limit(10).
                forEachOrdered(e -> System.out.println("[URL] "+e.getKey()+" / [No of Requests] "+e.getValue()));
    }

    public void sortUnsuccessfulPagesHashMap() {
        unsuccessfulReqPages.entrySet().
                stream().
                sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                limit(10).
                forEachOrdered(e -> System.out.println("[URL] " + e.getKey()));
    }

}

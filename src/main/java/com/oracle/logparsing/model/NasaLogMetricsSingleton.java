package com.oracle.logparsing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NasaLogMetricsSingleton {

    private static NasaLogMetricsSingleton nasaLogMetrics = new NasaLogMetricsSingleton();

    private HashMap<String, Integer> hosts = new HashMap<>();
    private HashMap<String, Integer> requestedPages = new HashMap<>();
    private HashMap<String, Integer> unsuccessfulReqPages = new HashMap<>();

    private List<LoggedRequest> requestObjs = new ArrayList<>();

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

    public List<LoggedRequest> getRequestObjs() {
        return requestObjs;
    }

    public void setRequestObjs(List<LoggedRequest> requestObjs) {
        this.requestObjs = requestObjs;
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

    public HashMap<String, Integer> getHosts() {
        return hosts;
    }

    public void setHosts(HashMap<String, Integer> hosts) {
        this.hosts = hosts;
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

    public void addEntryInHosts(String hostname) {
        hosts.put(hostname, hosts.containsKey(hostname) ? hosts.get(hostname) + 1 : 1);
    }

    public void addEntryInRequestObjs(LoggedRequest loggedRequest) {
        requestObjs.add(loggedRequest);
    }

    public double computePercentage(boolean successful) {
        int noOfRequests = (successful) ? this.successfulRequests : this.unsuccessfulRequests;
        return (((double) noOfRequests) / ((double) this.totalRequests)) * 100;
    }

    public void findTopRequestedPagesFromHashMap() {
        requestedPages.entrySet().
                        stream().
                        sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                        limit(10)
                        .forEachOrdered(e ->
                        System.out.println("[URL] " + e.getKey() + " / [No of Requests] " + e.getValue()));
    }

    public void findTopUnsuccessfulPagesFromHashMap() {
        unsuccessfulReqPages.entrySet().
                        stream().
                        sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                        limit(10)
                        .forEachOrdered(e -> System.out.println("[URL] " + e.getKey()));
    }

    private List<Host> findTopTenHostsList() {
        List<Host> topHostsWithInfo = new ArrayList<>();

        List<String> topHosts = hosts.entrySet().
                stream().
                sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                limit(10).
                map(Map.Entry::getKey).
                collect(Collectors.toList());

        for(String topHost: topHosts){
            topHostsWithInfo.add(new Host(topHost));
        }

        return topHostsWithInfo;
    }

    public void findTopTenHosts() {
        List<Host> topTenHosts = findTopTenHostsList();

        for(Host host: topTenHosts) {
            host.getRequests().entrySet().
                    stream().
                    sorted(Map.Entry.<String, Long>comparingByValue().reversed()).
                    limit(5).
                    forEachOrdered(e ->
                    System.out.println("[URL] " + e.getKey() + " / [No of Requests] " + e.getValue()) );
        }
    }

}

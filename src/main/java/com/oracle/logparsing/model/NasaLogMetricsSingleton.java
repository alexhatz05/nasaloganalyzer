package com.oracle.logparsing.model;

import com.oracle.logparsing.util.ConsoleColors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NasaLogMetricsSingleton {

    private static final NasaLogMetricsSingleton nasaLogMetrics = new NasaLogMetricsSingleton();

    private static final String URL_CONST = "[URL] ";
    private static final String NO_OF_REQUESTS_CONST = " / [No of Requests] ";

    private final HashMap<String, Integer> hosts = new HashMap<>();
    private final HashMap<String, Integer> requestedPages = new HashMap<>();
    private final HashMap<String, Integer> unsuccessfulReqPages = new HashMap<>();

    private final List<LoggedRequest> requestObjs = new ArrayList<>();

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
    public List<LoggedRequest> getRequestObjs() {
        return requestObjs;
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

    public void setSuccessfulRequests(int successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public void setUnsuccessfulRequests(int unsuccessfulRequests) {
        this.unsuccessfulRequests = unsuccessfulRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
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
                        System.out.println( URL_CONST + e.getKey() + NO_OF_REQUESTS_CONST + e.getValue()));
    }

    public void findTopUnsuccessfulPagesFromHashMap() {
        unsuccessfulReqPages.entrySet().
                        stream().
                        sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                        limit(10)
                        .forEachOrdered(e -> System.out.println(URL_CONST + e.getKey()));
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
            System.out.println(ConsoleColors.PURPLE_BOLD + "[Hostname] " + host.getHostName() +ConsoleColors.RESET_COLOUR);

            host.getRequests().entrySet().
                    stream().
                    sorted(Map.Entry.<String, Long>comparingByValue().reversed()).
                    limit(5).
                    forEachOrdered(e ->
                    System.out.println(URL_CONST + e.getKey() +  NO_OF_REQUESTS_CONST+ e.getValue()) );
        }
    }

}

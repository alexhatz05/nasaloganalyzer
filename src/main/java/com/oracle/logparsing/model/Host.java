package com.oracle.logparsing.model;

import java.util.Map;
import java.util.stream.Collectors;

public class Host {

    private String hostName;
    private Map<String, Long> totalRequests;
    private NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

    public Host(String hostName){
        this.hostName = hostName;
        this.totalRequests = findRequestsPerHost();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Map<String, Long> getRequests() {
        return totalRequests;
    }

    public void setRequests(Map<String, Long> requests) {
        this.totalRequests = requests;
    }

    /* Util */
    public void addEntryInHostRequests(String hostname) {
        totalRequests.put(hostname, totalRequests.containsKey(hostname) ? totalRequests.get(hostname) + 1 : 1);
    }

    private Map<String, Long> findRequestsPerHost() {
        return metricsSingleton.getRequestObjs().
                stream().
                filter(loggedRequest -> loggedRequest.getHost().
                equals(this.hostName)).
                map(LoggedRequest::getRequest).
                collect(Collectors.groupingBy(k->k, Collectors.counting()));
    }

}

package com.oracle.logparsing.model;

import java.util.Map;
import java.util.stream.Collectors;

public class Host {

    private final String hostName;
    private final Map<String, Long> totalRequests;
    private final NasaLogMetricsSingleton metricsSingleton = NasaLogMetricsSingleton.getInstance();

    public Host(String hostName){
        this.hostName = hostName;
        this.totalRequests = findRequestsOfHost();
    }

    public String getHostName() {
        return hostName;
    }

    public Map<String, Long> getRequests() {
        return totalRequests;
    }

    /* Util */
    public Map<String, Long> findRequestsOfHost() {
        return metricsSingleton.getRequestObjs().
                stream().
                filter(loggedRequest -> loggedRequest.getHost().
                equals(this.hostName)).
                map(LoggedRequest::getRequestURL).
                collect(Collectors.groupingBy(k->k, Collectors.counting()));
    }

}

package com.oracle.logparsing.model;

public class LoggedRequest {

    int requestCode;
    private String host;
    private String timestamp;
    private String request;

    public LoggedRequest(String host, String request, int requestCode) {
        this.host = host;
        this.request = request;
        this.requestCode = requestCode;
    }

    /*Getters and Setters*/

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    /*Methods*/

    public boolean isSuccessful() {
        return (this.requestCode >= 200 && this.requestCode < 400) ? true : false;
    }

}

package com.oracle.logparsing.model;

public class LoggedRequest {

    int requestCode;
    private String host;
    private String timestamp;
    private String request;
    private String requestMethod;
    private String requestURL;
    private String requestProtocol;

    public LoggedRequest(String host, String request, int requestCode) {
        this.host = host;
        this.request = request;
        this.requestCode = requestCode;

        this.splitRequestInParts();
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

    public String getRequestMethod() { return requestMethod; }

    public void setRequestMethod(String requestMethod) { this.requestMethod = requestMethod; }

    public String getRequestURL() { return requestURL; }

    public void setRequestURL(String requestURL) { this.requestURL = requestURL; }

    public String getRequestProtocol() { return requestProtocol; }

    public void setRequestProtocol(String requestProtocol) { this.requestProtocol = requestProtocol; }

    /*Methods*/

    public boolean isSuccessful() {
        return (this.requestCode >= 200 && this.requestCode < 400) ? true : false;
    }

    /*Util*/
    private void splitRequestInParts(){
        String[] requestParts = this.request.split(" ");
        this.setRequestMethod(requestParts[0]);
        this.setRequestURL(requestParts[1]);
        this.setRequestProtocol(requestParts[2]);
    }

}

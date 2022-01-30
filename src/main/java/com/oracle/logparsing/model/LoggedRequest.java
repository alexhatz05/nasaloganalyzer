package com.oracle.logparsing.model;

public class LoggedRequest {

    private int requestCode;
    private final String host;
    private final String request;
    private String requestURL;

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

    public String getRequestURL() { return requestURL; }

    public void setRequestURL(String requestURL) { this.requestURL = requestURL; }

    public void setRequestCode(int code) { this.requestCode = code; }

    public boolean isSuccessful() {
        return (this.requestCode >= 200 && this.requestCode < 400);
    }

    /*Util*/
    private void splitRequestInParts(){
        String[] requestParts = this.request.split(" ");
        this.setRequestURL(requestParts[1]);
    }

}

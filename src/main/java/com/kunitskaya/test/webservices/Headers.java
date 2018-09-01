package com.kunitskaya.test.webservices;

public enum Headers {
    CONTENT_TYPE("Content-Type");

    String header;

    Headers(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}

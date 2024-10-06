package com.donajul.gateway.filter;

public enum HeaderNames {

    TOKEN("Token"),
    REFRESH_TOKEN("Refresh-token"),
    PUBLIC_KEY("Public-key");

    private final String value;

    HeaderNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

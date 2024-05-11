package com.springboot.empc.security;

public enum AuthScope {

    SUPER_ADMIN("84708"),
    ADMIN("87453"),
    USER("74534");

    final String value;

    AuthScope(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }

}

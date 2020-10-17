package com.doruk.identity.interfaces;

public enum ErrorCode {

    IDINVALID("IS1000");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}

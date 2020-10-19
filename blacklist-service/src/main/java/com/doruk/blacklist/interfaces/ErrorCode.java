package com.doruk.blacklist.interfaces;

public enum ErrorCode {

    BORNYEARINVALID("CTS1001"),
    IDENTITYNUMBERINVALID("CTS1002"),
    NAMEINVALID("CTS1003"),
    MONTHLYINCOMEINVALID("CTS1004"),
    SURNAMEINVALID("CTS1005"),
    PHONENUMBERINVALID("CTS1006");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}

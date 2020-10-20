package com.doruk.creditapproval.domain;

public class IdentityInformationNotMatchException extends RuntimeException {

    private String identityNumber;

    private IdentityInformationNotMatchException(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public static IdentityInformationNotMatchException create(final String identityNumber) {
        return new IdentityInformationNotMatchException(identityNumber);
    }

    @Override
    public String getMessage() {
        return "Identity number not match, identity number: " + this.identityNumber;
    }
}

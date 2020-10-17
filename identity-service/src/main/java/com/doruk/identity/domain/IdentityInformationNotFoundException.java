package com.doruk.identity.domain;

public class IdentityInformationNotFoundException extends IllegalArgumentException{

    private String id;

    private IdentityInformationNotFoundException(String id) {
        this.id = id;
    }

    public static IdentityInformationNotFoundException create(String id) {
        return new IdentityInformationNotFoundException(id);
    }

    @Override
    public String toString() {
        return "Identity with id: " + this.id + " does not exist in system.";
    }
}

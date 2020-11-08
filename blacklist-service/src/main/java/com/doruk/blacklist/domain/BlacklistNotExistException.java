package com.doruk.blacklist.domain;

import java.util.NoSuchElementException;

public class BlacklistNotExistException extends NoSuchElementException {

    private final String identity;

    private BlacklistNotExistException(String identity) {
        this.identity = identity;
    }

    public static BlacklistNotExistException create(String identity) {
        return new BlacklistNotExistException(identity);
    }

    @Override
    public String getMessage() {
        return "Blacklist record not found for id : " + this.identity;
    }
}

package com.doruk.creditapproval.domain;

public class CreditApplicationNotFoundException extends RuntimeException {

    private Integer id;

    private CreditApplicationNotFoundException(Integer id) {
        this.id = id;
    }

    public static CreditApplicationNotFoundException create(Integer id) {
        return new CreditApplicationNotFoundException(id);
    }

    @Override
    public String toString() {
        return "Credit Application with id: " + this.id + " does not exist in system.";
    }
}

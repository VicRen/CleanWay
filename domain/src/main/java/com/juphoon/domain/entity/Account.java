package com.juphoon.domain.entity;

public class Account extends User {

    private boolean isFreeProOrdered;
    private boolean isFreeGroupCreated;

    public Account(String phone) {
        super(phone);
    }

    public boolean isFreeProOrdered() {
        return isFreeProOrdered;
    }

    public void setFreeProOrdered(boolean freeProOrdered) {
        isFreeProOrdered = freeProOrdered;
    }

    public boolean isFreeGroupCreated() {
        return isFreeGroupCreated;
    }

    public void setFreeGroupCreated(boolean freeGroupCreated) {
        isFreeGroupCreated = freeGroupCreated;
    }
}

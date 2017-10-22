package com.juphoon.domain.entity;

public class Client {

    private String userId;
    private final int state;

    private boolean hasFreeGroup;

    public Client(int state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }
}

package com.juphoon.data.entity;

import com.google.gson.annotations.SerializedName;

public class FreeContactEntity {

    @SerializedName("servnumber")
    private String phone;

    @SerializedName("region")
    private String region;

    @SerializedName("startdate")
    private String startDate;

    @SerializedName("enddate")
    private String endDate;

    @SerializedName("mainflag")
    private int mainFlag;

    public FreeContactEntity() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isOwner() {
        return mainFlag == 1;
    }
}

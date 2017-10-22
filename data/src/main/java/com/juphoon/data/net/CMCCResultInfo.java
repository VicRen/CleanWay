package com.juphoon.data.net;

import com.google.gson.annotations.SerializedName;

public class CMCCResultInfo {

    @SerializedName("rettype")
    private int resultType;

    @SerializedName("retmsg")
    private String resultMessage;

    @SerializedName("retcode")
    private int resultCode;

    public int getResultType() {
        return resultType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }
}

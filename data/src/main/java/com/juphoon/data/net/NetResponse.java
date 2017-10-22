package com.juphoon.data.net;

import com.google.gson.annotations.SerializedName;

public abstract class NetResponse<T> {

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = 0;
    public static final int CODE_NOT_GZMOBILE = -2;

    @SerializedName("code")
    protected int code;

    @SerializedName("data")
    protected T data;

    @SerializedName("error")
    protected String error;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}

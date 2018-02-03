package com.cmcc.cleanway.exception;

import android.content.Context;

import com.juphoon.data.exception.NetworkConnectionException;
import com.juphoon.data.exception.UserNotFoundException;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception) {
        String message = "";
        if (exception instanceof NetworkConnectionException) {
            message = "";
        } else if (exception instanceof UserNotFoundException) {
            message = "";
        }
        return message;
    }

}

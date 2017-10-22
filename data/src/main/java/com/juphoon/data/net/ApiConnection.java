package com.juphoon.data.net;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection implements Callable<String> {

    public static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    public static ApiConnection createPOST(String url, Map<String, String> body) throws MalformedURLException {
        return new ApiConnection(url, body);
    }

    private URL url;
    private String response;
    private Map<String, String> requestBody;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    private ApiConnection(String url, Map<String, String> requestBody) throws MalformedURLException {
        this.url = new URL(url);
        this.requestBody = requestBody;
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    public String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        OkHttpClient okHttpClient = createClient();
        final Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (requestBody == null || requestBody.isEmpty()) {
            builder.get();
        } else {
            FormBody.Builder formBody = new FormBody.Builder();
            for (String key : requestBody.keySet()) {
                formBody.add(key, requestBody.get(key));
            }
            builder.post(formBody.build());
        }
        final Request request = builder.build();
        try {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(15000, TimeUnit.MILLISECONDS);
        return builder.build();
    }
}

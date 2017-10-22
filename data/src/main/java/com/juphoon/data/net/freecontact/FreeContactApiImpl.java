package com.juphoon.data.net.freecontact;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.juphoon.data.entity.FreeContactEntity;
import com.juphoon.data.entity.mapper.FreeContactEntityJsonMapper;
import com.juphoon.data.exception.NetworkConnectionException;
import com.juphoon.data.net.ApiConnection;
import com.juphoon.domain.utils.StringUtils;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class FreeContactApiImpl implements FreeContactApi {

    private final Context context;
    private final FreeContactEntityJsonMapper freeContactEntityJsonMapper;

    public FreeContactApiImpl(Context context, FreeContactEntityJsonMapper freeContactEntityJsonMapper) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }
        this.context = context.getApplicationContext();
        this.freeContactEntityJsonMapper = freeContactEntityJsonMapper;
    }

    @Override
    public Observable<Boolean> createFreeContact(final String phone) {
        return null;
    }

    @Override
    public Observable<Collection<FreeContactEntity>> freeContactEntityList(final String phone) {
        return Observable.create(emitter -> {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:api.freeContactEntityList");
            if (isThereInternetConnection()) {
                try {
                    String freeContactResponse = getFreeContactEntitiesFromApi(phone);
                    if (StringUtils.isEmpty(freeContactResponse)) {
                        emitter.onError(new NetworkConnectionException());
                    } else {
                        emitter.onNext(freeContactEntityJsonMapper
                                .transformFreeContactEntity(freeContactResponse)
                                .getFreeContactList(phone));
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<FreeContactEntity> addFreeContact(final String phone, final String memberPhone) {
        return null;
    }

    @Override
    public Observable<String> removeFreeContact(final String phone, final String memberPhone) {
        return null;
    }

    private String getFreeContactEntitiesFromApi(String phone) throws MalformedURLException {
        Map<String, String> body = new HashMap<>();
        body.put("operationReq", "richmanmemberquey");
        body.put("serverPhone", phone);
        return ApiConnection.createPOST(API_URL_GET_FREE_CONTACT_LIST, body).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}

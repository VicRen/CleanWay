package com.juphoon.data.web.client;

import android.content.Context;
import android.util.Log;

import com.juphoon.JCEngine;
import com.juphoon.data.JCEngineManager;
import com.juphoon.data.USEventHandler;
import com.juphoon.data.exception.NetworkConnectionException;
import com.juphoon.domain.entity.Account;
import com.juphoon.domain.entity.User;
import com.justalk.cloud.lemon.Mtc;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class ClientApiImpl implements ClientApi {

    @Inject
    public ClientApiImpl(Context context) {
    }

    @Override
    public Observable<String> login(String user) {
        return Observable.create(emitter -> {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl.login");
            int ret = JCEngineManager.getInstance().getJCEngine().login(user, user);
            if (ret == Mtc.ZFAILED) {
                emitter.onError(new IOException());
            } else {
                JCEngineManager.getInstance().getEventHandler().addEventHandler(new USEventHandler.SimpleEventHandler() {
                    @Override
                    public void onConnected(String userId) {
                        JCEngineManager.getInstance().getEventHandler().removeEventHandler(this);
                        emitter.onNext(user);
                    }

                    @Override
                    public void onError(@JCEngine.ErrorCode int errorCode) {
                        JCEngineManager.getInstance().getEventHandler().removeEventHandler(this);
                        emitter.onError(new NetworkConnectionException());
                    }
                });
            }
        });
    }

    @Override
    public Observable<User> getOwnUser() {
        return Observable.create(emitter -> {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl.getOwnUser");
            emitter.onNext(new Account("13826079204"));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<String> getOwnPhone() {
        return Observable.create(emitter -> {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl.getOwnPhone");
            emitter.onNext("13826079204");
            emitter.onComplete();
        });
    }
}

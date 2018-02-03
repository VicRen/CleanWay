package com.juphoon.data.web.client;

import android.content.Context;
import android.util.Log;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.juphoon.JCEngine;
import com.juphoon.data.JCEngineManager;
import com.juphoon.data.USEventHandler;
import com.juphoon.domain.entity.Account;
import com.juphoon.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class ClientApiImpl implements ClientApi {

    private int state;
    private BehaviorRelay<Integer> stateRelay;

    @Inject
    ClientApiImpl(Context context) {
        Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl()");
        JCEngineManager.getInstance().initialize(context, "97346350260773bfd2544096");
        this.state = JCEngineManager.getInstance().getJCEngine().getState();
    }

    @Override
    public BehaviorRelay<Integer> engineState() {
        if (stateRelay == null) {
            stateRelay = BehaviorRelay.createDefault(state);
        }
        return stateRelay;
    }

    @Override
    public Observable<String> login(String user) {
        return Observable.create(emitter -> {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl.login");
            int ret = JCEngineManager.getInstance().getJCEngine().login(user, user);
            if (stateRelay != null) {
                stateRelay.accept(JCEngineManager.getInstance().getJCEngine().getState());
            }
//            if (ret == Mtc.ZFAILED) {
//                emitter.onError(new IOException());
//            } else {
//                JCEngineManager.getInstance().getEventHandler().addEventHandler(new USEventHandler.SimpleEventHandler() {
//                    @Override
//                    public void onConnected(String userId) {
//                        JCEngineManager.getInstance().getEventHandler().removeEventHandler(this);
//                        emitter.onNext(user);
//                    }
//
//                    @Override
//                    public void onError(@JCEngine.ErrorCode int errorCode) {
//                        JCEngineManager.getInstance().getEventHandler().removeEventHandler(this);
//                        emitter.onError(new NetworkConnectionException());
//                    }
//                });
//            }
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

    @Override
    public Observable<String> listenEvent() {
        return Observable.create((ObservableOnSubscribe<String>) e ->
                JCEngineManager.getInstance().getEventHandler().addEventHandler(new USEventHandler.SimpleEventHandler() {
                    @Override
                    public void onConnected(String userId) {
                        Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:ClientApiImpl.listenEvent");
                        e.onNext("onConnected userId:" + userId);
                        if (stateRelay != null) {
                            stateRelay.accept(JCEngineManager.getInstance().getJCEngine().getState());
                        }
                    }

                    @Override
                    public void onError(@JCEngine.ErrorCode int errorCode) {
                        e.onError(new Exception("errorCode:" + errorCode));
                    }
                })
        ).share();
    }
}

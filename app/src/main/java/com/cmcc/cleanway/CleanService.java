package com.cmcc.cleanway;

import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.juphoon.domain.interactor.ClientListenEvent;
import com.juphoon.domain.interactor.ClientLogin;
import com.juphoon.domain.interactor.DefaultObserver;

import javax.inject.Inject;

import dagger.android.DaggerService;
import io.reactivex.annotations.NonNull;

public class CleanService extends DaggerService {

    @Inject
    ClientLogin clientLogin;

    @Inject
    ClientListenEvent clientListenEvent;

    @Override
    public void onCreate() {
        super.onCreate();
        clientListenEvent.execute(new EventObserver(), null);
        clientLogin.execute(new LoginObserver(), ClientLogin.Param.forUser("yy"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               clientLogin.execute(new LoginObserver(), ClientLogin.Param.forUser("yy"));
            }
        }, 3000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clientLogin.dispose();
        clientListenEvent.dispose();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final class LoginObserver extends DefaultObserver<String> {

        @Override
        public void onNext(@NonNull String s) {
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " stop service");
            stopSelf();
        }
    }

    private final class EventObserver extends DefaultObserver<String> {

        @Override
        public void onNext(@NonNull String s) {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:CleanService.EventObserver.onNext:" + s);
            super.onNext(s);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:CleanService.EventObserver.onError:" + e);
            super.onError(e);
        }
    }
}

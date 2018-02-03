package com.cmcc.cleanway;

import android.content.Intent;

import com.cmcc.cleanway.internal.di.components
        .DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AndroidApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        initializeLeakDetection();
//        applicationComponent.clientRepository().listenEvent().subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + "AndroidApplication.onNext:" + s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });
//        applicationComponent.clientRepository().status().subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + "AndroidApplication.status.onNext:" + integer);
//            }
//        });
        startService(new Intent(this, CleanService.class));
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}

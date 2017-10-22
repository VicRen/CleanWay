package com.cmcc.cleanway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cmcc.cleanway.internal.di.components.ApplicationComponent;
import com.cmcc.cleanway.internal.di.modules.ActivityModule;
import com.juphoon.domain.entity.User;
import com.juphoon.domain.interactor.ClientGetAccount;
import com.juphoon.domain.interactor.ClientLogin;
import com.juphoon.domain.interactor.DefaultObserver;
import com.juphoon.domain.interactor.UserGetFreeContacts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

public class MainActivity extends AppCompatActivity {

    @Inject
    UserGetFreeContacts userGetFreeContacts;

    @Inject
    ClientGetAccount clientGetAccount;

    @Inject
    ClientLogin clientLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        setContentView(R.layout.activity_main);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public void onStart(View view) {
//        getUserAccount();
//        getFreeContactList();
//        login("yy");
        multiCasting();
    }

    private void getFreeContactList() {
        userGetFreeContacts.execute(new DefaultObserver<List<User>>() {

            @Override
            public void onComplete() {
                log("getFreeContactList.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull List<User> users) {
                log("getFreeContactList.onNext");
                super.onNext(users);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("getFreeContactList.onError");
            }
        }, null);
    }

    private void getUserAccount() {
        clientGetAccount.execute(new DefaultObserver<User>() {

            @Override
            public void onComplete() {
                log("getUserAccount.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull User user) {
                log("getUserAccount.onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("getUserAccount.onError");
            }
        }, null);
    }

    private void login(String userId) {
        clientLogin.execute(new DefaultObserver<String>() {

            @Override
            public void onComplete() {
                log("login.onComplete");
                super.onComplete();
            }

            @Override
            public void onNext(@NonNull String s) {
                log("login.onNext:" + s);
                super.onNext(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log("login.onError");
            }
        }, ClientLogin.Param.forUser(userId));
    }

    private void multiCasting() {
        ConnectableObservable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).publish();
        observable.connect();
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("observable subscribe 1:" + aLong);
            }
        });
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log("observable subscribe 2:" + aLong);
            }
        });
    }

    private void log(String msg) {
        Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + " message:" + msg);
    }
}

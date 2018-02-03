package com.cmcc.cleanway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        mainPresenter.takeView(this);
    }

    public void onStart(View view) {
//        getUserAccount();
//        getFreeContactList();
//        listenEvent();
//        login("yy");
//        multiCasting();
        mainPresenter.buttonClicked();
    }
}

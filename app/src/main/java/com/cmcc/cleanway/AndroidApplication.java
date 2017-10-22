package com.cmcc.cleanway;

import android.app.Application;

import com.cmcc.cleanway.internal.di.components.ApplicationComponent;
import com.cmcc.cleanway.internal.di.components.DaggerApplicationComponent;
import com.cmcc.cleanway.internal.di.modules.ApplicationModule;
import com.juphoon.data.JCEngineManager;
import com.squareup.leakcanary.LeakCanary;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        JCEngineManager.getInstance().initialize(this, "97346350260773bfd2544096");
        initializeInjector();
//        initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}

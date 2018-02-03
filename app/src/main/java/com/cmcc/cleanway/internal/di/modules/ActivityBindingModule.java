package com.cmcc.cleanway.internal.di.modules;

import com.cmcc.cleanway.MainActivity;
import com.cmcc.cleanway.internal.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}

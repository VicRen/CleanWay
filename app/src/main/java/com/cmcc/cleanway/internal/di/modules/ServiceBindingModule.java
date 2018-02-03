package com.cmcc.cleanway.internal.di.modules;

import com.cmcc.cleanway.CleanService;
import com.cmcc.cleanway.internal.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = CleanServiceModule.class)
    abstract CleanService cleanService();

}

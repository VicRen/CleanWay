package com.cmcc.cleanway.internal.di.modules;

import com.cmcc.cleanway.MainContract;
import com.cmcc.cleanway.MainPresenter;
import com.cmcc.cleanway.internal.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainActivityModule {

    @ActivityScope
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}

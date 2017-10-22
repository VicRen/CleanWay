package com.cmcc.cleanway.internal.di.components;

import com.cmcc.cleanway.MainActivity;
import com.cmcc.cleanway.internal.di.PerActivity;
import com.cmcc.cleanway.internal.di.modules.ActivityModule;
import com.cmcc.cleanway.internal.di.modules.UserModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);
}

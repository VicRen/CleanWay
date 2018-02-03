/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmcc.cleanway.internal.di.components;

import android.app.Application;

import com.cmcc.cleanway.internal.di.modules.ActivityBindingModule;
import com.cmcc.cleanway.internal.di.modules.ApplicationModule;
import com.cmcc.cleanway.internal.di.modules.ClientRepositoryModule;
import com.cmcc.cleanway.internal.di.modules.ServiceBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
        ApplicationModule.class,
        ClientRepositoryModule.class,
        ActivityBindingModule.class,
        ServiceBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

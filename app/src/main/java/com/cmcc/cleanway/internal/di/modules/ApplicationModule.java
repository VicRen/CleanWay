/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmcc.cleanway.internal.di.modules;

import android.app.Application;
import android.content.Context;

import com.cmcc.cleanway.UIThread;
import com.juphoon.data.executor.JobExecutor;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public abstract class ApplicationModule {

    //expose Application as an injectable context
    @Singleton
    @Binds
    abstract Context provideApplicationContext(Application application);

    @Singleton
    @Binds
    abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Singleton
    @Binds
    abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);

//    private final AndroidApplication application;
//
//    public ApplicationModule(AndroidApplication application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    Context provideApplicationContext() {
//        return this.application;
//    }
//
//    @Provides
//    @Singleton
//    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
//        return jobExecutor;
//    }
//
//    @Provides
//    @Singleton
//    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
//        return uiThread;
//    }
//
//    @Provides
//    @Singleton
//    ClientApi provideClientApi(ClientApiImpl clientApi) {
//        return clientApi;
//    }
//
//    @Provides
//    @Singleton
//    UserCache provideFreeContactCache(UserCacheImpl freeContactCache) {
//        return freeContactCache;
//    }
//
//    @Provides
//    @Singleton
//    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
//        return userDataRepository;
//    }
//
//    @Provides
//    @Singleton
//    ClientRepository provideClientRepository(ClientDataRepository clientDataRepository) {
//        return clientDataRepository;
//    }
}

package com.cmcc.cleanway.internal.di.modules;

import com.juphoon.data.repository.ClientDataRepository;
import com.juphoon.data.web.client.ClientApi;
import com.juphoon.data.web.client.ClientApiImpl;
import com.juphoon.domain.repository.ClientRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ClientRepositoryModule {

    @Singleton
    @Binds
    abstract ClientApi provideClientApi(ClientApiImpl clientApi);

    @Singleton
    @Binds
    abstract ClientRepository provideClientRepository(ClientDataRepository clientRepository);
}

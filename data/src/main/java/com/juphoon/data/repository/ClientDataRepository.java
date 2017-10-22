package com.juphoon.data.repository;

import com.juphoon.data.repository.datasource.ClientDataStoreFactory;
import com.juphoon.data.web.client.ClientApi;
import com.juphoon.domain.entity.User;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ClientDataRepository implements ClientRepository {

    private final ClientApi clientApi;
    private final ClientDataStoreFactory clientDataStoreFactory;
    private final PostExecutionThread postExecutionThread;
    private final ThreadExecutor threadExecutor;

    @Inject
    ClientDataRepository(ClientApi clientApi, ClientDataStoreFactory clientDataStoreFactory,
                         PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.clientApi = clientApi;
        this.clientDataStoreFactory = clientDataStoreFactory;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<String> login(final String userId) {
        return clientApi.login(userId)
                .subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<Integer> status() {
        return null;
    }

    @Override
    public Observable<String> ownAccountPhone() {
        return clientDataStoreFactory.createCloudDataStore(clientApi).ownPhoneNumber()
                .subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<User> account() {
        return clientDataStoreFactory.createCloudDataStore(clientApi).ownUser();
    }
}

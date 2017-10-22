package com.juphoon.data.repository.datasource;

import com.juphoon.data.web.client.ClientApi;
import com.juphoon.domain.entity.User;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ClientCloudDataStore implements ClientDataStore {

    private final ClientApi clientApi;
    private final PostExecutionThread postExecutionThread;
    private final ThreadExecutor threadExecutor;

    ClientCloudDataStore(ClientApi clientApi, PostExecutionThread postExecutionThread,
                         ThreadExecutor threadExecutor) {
        this.clientApi = clientApi;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<String> login(final String userId) {
        return clientApi.login(userId).subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<User> ownUser() {
        return clientApi.getOwnUser()
                .subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<String> ownPhoneNumber() {
        return clientApi.getOwnPhone();
    }
}

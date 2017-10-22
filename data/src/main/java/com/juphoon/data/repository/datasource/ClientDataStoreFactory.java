package com.juphoon.data.repository.datasource;

import com.juphoon.data.web.client.ClientApi;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClientDataStoreFactory {

    private final PostExecutionThread postExecutionThread;
    private final ThreadExecutor threadExecutor;

    @Inject
    ClientDataStoreFactory(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    public ClientDataStore create() {
        return null;
    }

    public ClientDataStore createCloudDataStore(ClientApi clientApi) {
        return new ClientCloudDataStore(clientApi, postExecutionThread, threadExecutor);
    }
}

package com.juphoon.domain.interactor;

import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ClientListenEvent extends UseCase<String, Void> {

    private final ClientRepository clientRepository;

    @Inject
    ClientListenEvent(ClientRepository clientRepository,
                      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.clientRepository = clientRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Void aVoid) {
        return clientRepository.listenEvent();
    }
}

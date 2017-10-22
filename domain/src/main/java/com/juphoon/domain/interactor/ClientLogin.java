package com.juphoon.domain.interactor;

import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ClientLogin extends UseCase<String, ClientLogin.Param> {

    private final ClientRepository clientRepository;

    @Inject
    ClientLogin(ClientRepository clientRepository,
                ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.clientRepository = clientRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Param param) {
        return clientRepository.login(param.userId).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String id) throws Exception {
                System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.login.accept:" + id);
            }
        });
    }

    public static final class Param {

        private final String userId;

        private Param(String userId) {
            this.userId = userId;
        }

        public static Param forUser(String userId) {
            return new Param(userId);
        }
    }
}

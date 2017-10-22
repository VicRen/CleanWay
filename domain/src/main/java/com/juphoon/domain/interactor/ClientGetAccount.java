package com.juphoon.domain.interactor;

import com.juphoon.domain.entity.User;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class ClientGetAccount extends UseCase<User, Void> {

    private ClientRepository clientRepository;

    @Inject
    ClientGetAccount(ClientRepository clientRepository, ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.clientRepository = clientRepository;
    }

    @Override
    Observable<User> buildUseCaseObservable(Void unused) {
        System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.ClientGetAccount");
        return clientRepository.account().map(new Function<User, User>() {
            @Override
            public User apply(@NonNull User user) throws Exception {
                System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.ClientGetAccount.apply");
                return user;
            }
        });
    }
}

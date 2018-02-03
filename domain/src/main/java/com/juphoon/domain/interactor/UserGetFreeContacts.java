package com.juphoon.domain.interactor;

import com.juphoon.domain.entity.User;
import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;
import com.juphoon.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class UserGetFreeContacts extends UseCase<List<User>, Void> {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    UserGetFreeContacts(ClientRepository clientRepository, UserRepository userRepository,
                        ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    Observable<List<User>> buildUseCaseObservable(final Void unused) {
        return clientRepository.ownAccountPhone().concatMap(new Function<String, ObservableSource<List<User>>>() {
            @Override
            public ObservableSource<List<User>> apply(@NonNull String phone) throws Exception {
                System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.ownAccountPhone");
                return userRepository.freeContactList(phone).map(new Function<Collection<User>, List<User>>() {
                    @Override
                    public List<User> apply(@NonNull Collection<User> users) throws Exception {
                        System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.apply");
                        return new ArrayList<>(users);
                    }
                });
            }
        });
    }

}

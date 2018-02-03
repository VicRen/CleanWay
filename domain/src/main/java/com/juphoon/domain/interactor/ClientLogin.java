package com.juphoon.domain.interactor;

import com.juphoon.domain.executor.PostExecutionThread;
import com.juphoon.domain.executor.ThreadExecutor;
import com.juphoon.domain.repository.ClientRepository;
import com.juphoon.domain.utils.StringUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
        return clientRepository.user().flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String s) throws Exception {
                if (StringUtils.isEmpty(s)) {
                    return Observable.error(new Exception());
                }
                return clientRepository.login(s)
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                System.out.println("RxJava Thread:" + Thread.currentThread().getName() + " message:buildUseCaseObservable.login.accept:" + s);
                            }
                        });
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

package com.juphoon.data.repository;

import android.util.Log;

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
    private final PostExecutionThread postExecutionThread;
    private final ThreadExecutor threadExecutor;

    @Inject
    ClientDataRepository(ClientApi clientApi,
                         PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.clientApi = clientApi;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<String> user() {
        return Observable.create(e -> e.onNext("yy"));
    }

    @Override
    public Observable<String> login(final String userId) {
        return clientApi.login(userId)
                .subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<Integer> status() {
        return clientApi.engineState()
                .subscribeOn(postExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor));
    }

    @Override
    public Observable<String> ownAccountPhone() {
        return null;
    }

    @Override
    public Observable<User> account() {
        return null;
    }

    private Observable<String> listenEventObserver;

    @Override
    public Observable<String> listenEvent() {
        if (listenEventObserver == null) {
            listenEventObserver = clientApi.listenEvent()
                    .subscribeOn(postExecutionThread.getScheduler())
                    .observeOn(Schedulers.from(threadExecutor))
                    .doOnNext(s -> Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + "ClientDataRepository.listenEvent.share"))
                    .share();
        }
        return listenEventObserver
                .doOnNext(s -> Log.d("RxJava", "Thread:" + Thread.currentThread().getName() + "ClientDataRepository.listenEvent"));
    }
}

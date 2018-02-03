package com.juphoon.data.web.client;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.juphoon.domain.entity.User;

import io.reactivex.Observable;

public interface ClientApi {

    BehaviorRelay<Integer> engineState();

    Observable<String> login(final String user);

    Observable<User> getOwnUser();

    Observable<String> getOwnPhone();

    Observable<String> listenEvent();
}

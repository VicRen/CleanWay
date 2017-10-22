package com.juphoon.data.web.client;

import com.juphoon.domain.entity.User;

import io.reactivex.Observable;

public interface ClientApi {

    Observable<String> login(final String user);

    Observable<User> getOwnUser();

    Observable<String> getOwnPhone();
}

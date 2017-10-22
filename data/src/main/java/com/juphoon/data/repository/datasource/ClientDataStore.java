package com.juphoon.data.repository.datasource;

import com.juphoon.domain.entity.User;

import io.reactivex.Observable;

public interface ClientDataStore {

    Observable<String> login(final String userId);

    Observable<User> ownUser();

    Observable<String> ownPhoneNumber();
}

package com.juphoon.data.repository.datasource;

import com.juphoon.domain.entity.User;

import io.reactivex.Observable;

class ClientDiskDataStore implements ClientDataStore {

    @Override
    public Observable<String> login(String userId) {
        return null;
    }

    @Override
    public Observable<User> ownUser() {
        return null;
    }

    @Override
    public Observable<String> ownPhoneNumber() {
        return null;
    }
}

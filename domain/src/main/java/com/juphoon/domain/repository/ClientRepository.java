package com.juphoon.domain.repository;

import com.juphoon.domain.entity.User;

import io.reactivex.Observable;

public interface ClientRepository {

    Observable<String> login(final String userId);

    Observable<Integer> status();

    Observable<String> ownAccountPhone();

    Observable<User> account();
}

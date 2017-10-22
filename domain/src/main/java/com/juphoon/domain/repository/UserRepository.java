package com.juphoon.domain.repository;

import com.juphoon.domain.entity.User;

import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {

    /**
     * Get an {@link Observable} which will emit the friends list of {@link User}.
     */
    Observable<List<User>> friendList();

    /**
     * Get an {@link Observable} which will emit the free contacts list of {@link User}.
     *
     * @param phone The own phone number.
     */
    Observable<Collection<User>> freeContactList(final String phone);

    /**
     * Get an {@link Observable} which will emit the certified account list of {@link User}.
     *
     * @param phone The own phone number.
     */
    Observable<List<User>> classmateList(final String phone);

    /**
     * Get an {@link Observable} which will emit a {@link User} object of the given number that
     * contains the cloud account infos.
     *
     * @param phone The phone number used to retrieve account data.
     */
    Observable<User> cloudUser(final String phone);

    Observable<User> freeContact(final String phone);

    Observable<User> certifiedUser(final String phone);

    Observable<List<User>> cloudUserList(final List<String> phone);

    Observable<List<User>> freeContactList(final List<String> phone);

    Observable<List<User>> certifiedUserList(final List<String> phone);
}

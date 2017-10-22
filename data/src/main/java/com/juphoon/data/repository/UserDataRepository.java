package com.juphoon.data.repository;

import com.juphoon.data.entity.mapper.FreeContactDataMapper;
import com.juphoon.data.repository.datasource.FreeContactDataStoreFactory;
import com.juphoon.domain.entity.User;
import com.juphoon.domain.repository.UserRepository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UserDataRepository implements UserRepository {

    private final FreeContactDataStoreFactory freeContactDataStoreFactory;
    private final FreeContactDataMapper freeContactDataMapper;

    @Inject
    UserDataRepository(FreeContactDataStoreFactory freeContactDataStoreFactory,
                       FreeContactDataMapper freeContactDataMapper) {
        this.freeContactDataStoreFactory = freeContactDataStoreFactory;
        this.freeContactDataMapper = freeContactDataMapper;
    }

    @Override
    public Observable<List<User>> friendList() {
        return null;
    }

    @Override
    public Observable<Collection<User>> freeContactList(String phone) {
        return freeContactDataStoreFactory.create().freeContactEntities(phone)
                .map(freeContactDataMapper::transform);
    }

    @Override
    public Observable<List<User>> classmateList(String phone) {
        return null;
    }

    @Override
    public Observable<User> cloudUser(String phone) {
        return null;
    }

    @Override
    public Observable<User> freeContact(String phone) {
        return null;
    }

    @Override
    public Observable<User> certifiedUser(String phone) {
        return null;
    }

    @Override
    public Observable<List<User>> cloudUserList(List<String> phone) {
        return null;
    }

    @Override
    public Observable<List<User>> freeContactList(List<String> phone) {
        return null;
    }

    @Override
    public Observable<List<User>> certifiedUserList(List<String> phone) {
        return null;
    }

}

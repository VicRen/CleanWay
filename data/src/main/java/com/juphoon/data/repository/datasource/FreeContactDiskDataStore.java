package com.juphoon.data.repository.datasource;

import com.juphoon.data.entity.FreeContactEntity;

import java.util.Collection;

import io.reactivex.Observable;

public class FreeContactDiskDataStore implements FreeContactDataStore {

    @Override
    public Observable<Collection<FreeContactEntity>> freeContactEntities(String phone) {
        return null;
    }
}

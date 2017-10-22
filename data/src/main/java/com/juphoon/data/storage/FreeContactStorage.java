package com.juphoon.data.storage;

import com.juphoon.data.entity.FreeContactEntity;

import java.util.Collection;

import io.reactivex.Observable;

public interface FreeContactStorage {

    Observable<FreeContactEntity> get(final String phone);

    void put(FreeContactEntity freeContactEntity);

    void put(Collection<FreeContactEntity> freeContactEntities);

    boolean isSaved(final String phone);
}

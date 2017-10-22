package com.juphoon.data.repository.datasource;

import com.juphoon.data.entity.FreeContactEntity;

import java.util.Collection;

import io.reactivex.Observable;

public interface FreeContactDataStore {

    Observable<Collection<FreeContactEntity>> freeContactEntities(String phone);
}

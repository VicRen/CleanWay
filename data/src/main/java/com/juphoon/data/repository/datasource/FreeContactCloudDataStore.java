package com.juphoon.data.repository.datasource;

import com.juphoon.data.entity.FreeContactEntity;
import com.juphoon.data.net.freecontact.FreeContactApi;

import java.util.Collection;

import io.reactivex.Observable;

public class FreeContactCloudDataStore implements FreeContactDataStore {

    private final FreeContactApi freeContactApi;

    FreeContactCloudDataStore(FreeContactApi freeContactApi) {
        this.freeContactApi = freeContactApi;
    }

    @Override
    public Observable<Collection<FreeContactEntity>> freeContactEntities(String phone) {
        return freeContactApi.freeContactEntityList(phone);
    }
}

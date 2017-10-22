package com.juphoon.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.juphoon.data.cache.UserCache;
import com.juphoon.data.entity.mapper.FreeContactEntityJsonMapper;
import com.juphoon.data.net.freecontact.FreeContactApi;
import com.juphoon.data.net.freecontact.FreeContactApiImpl;
import com.juphoon.data.net.freecontact.FreeContactDeserializer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FreeContactDataStoreFactory {

    private final Context context;
    private final UserCache userCache;

    @Inject
    FreeContactDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache) {
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    public FreeContactDataStore create() {
        if (userCache.isCached() && !userCache.isExpired()) {
            return new FreeContactDiskDataStore();
        } else {
            return createCloudStorage();
        }
    }

    public FreeContactDataStore createCloudStorage() {
        final FreeContactEntityJsonMapper freeContactEntityJsonMapper = new FreeContactEntityJsonMapper(new FreeContactDeserializer());
        final FreeContactApi freeContactApi = new FreeContactApiImpl(context, freeContactEntityJsonMapper);
        return new FreeContactCloudDataStore(freeContactApi);
    }
}

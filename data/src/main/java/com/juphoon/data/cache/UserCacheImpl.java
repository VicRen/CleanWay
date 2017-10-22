package com.juphoon.data.cache;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserCacheImpl implements UserCache {

    @Inject
    UserCacheImpl() {

    }

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}

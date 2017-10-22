package com.juphoon.data.entity.mapper;

import com.juphoon.data.entity.FreeContactEntity;
import com.juphoon.domain.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FreeContactDataMapper {

    @Inject
    FreeContactDataMapper() {
    }

    public User transform(FreeContactEntity freeContactEntity) {
        User user = null;
        if (freeContactEntity != null) {
            user = new User(freeContactEntity.getPhone());
        }
        return user;
    }

    public List<User> transform(Collection<FreeContactEntity> freeContactEntities) {
        final List<User> userList = new ArrayList<>();
        for (FreeContactEntity freeContactEntity : freeContactEntities) {
            final User user = transform(freeContactEntity);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}

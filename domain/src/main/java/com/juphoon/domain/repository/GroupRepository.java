package com.juphoon.domain.repository;

import com.juphoon.domain.entity.Group;

import java.util.List;

import io.reactivex.Observable;

public interface GroupRepository {

    Observable<Group> group(final String groupId);

    Observable<List<Group>> groups();

    Observable<Group> createGroup();

    Observable<String> removeGroup(String groupId);

    Observable<Group> updateGroup(Group group);
}

package com.juphoon.data.net.freecontact;

import com.juphoon.data.entity.FreeContactEntity;

import java.util.Collection;

import io.reactivex.Observable;

public interface FreeContactApi {

    String API_BASE_URL = "http://218.204.254.209:28811/miyou/api/student/";

    String API_URL_GET_FREE_CONTACT_LIST = API_BASE_URL + "eaopQuery/";

    Observable<Boolean> createFreeContact(final String phone);

    Observable<Collection<FreeContactEntity>> freeContactEntityList(final String phone);

    Observable<FreeContactEntity> addFreeContact(final String phone, final String memberPhone);

    Observable<String> removeFreeContact(final String phone, final String memberPhone);
}

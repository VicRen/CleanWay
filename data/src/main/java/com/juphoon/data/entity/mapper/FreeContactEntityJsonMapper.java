package com.juphoon.data.entity.mapper;

import com.google.gson.JsonSyntaxException;
import com.juphoon.data.net.freecontact.FreeContactDeserializer;
import com.juphoon.data.net.freecontact.FreeContactQueryResponse;

import javax.inject.Inject;

public class FreeContactEntityJsonMapper {

    private final FreeContactDeserializer deserializer;

    @Inject
    public FreeContactEntityJsonMapper(FreeContactDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public FreeContactQueryResponse transformFreeContactEntity(String freeContactListJsonResponse) throws JsonSyntaxException {
        return deserializer.deserialize(freeContactListJsonResponse);
    }
}

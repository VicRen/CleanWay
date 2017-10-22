package com.juphoon.data.net.freecontact;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FreeContactDeserializer {

    private final Gson gson = new Gson();

    public FreeContactDeserializer() {
    }

    public FreeContactQueryResponse deserialize(String string) {
        FreeContactQueryResponse freeContactQueryResponse = gson.fromJson(string, FreeContactQueryResponse.class);
        try {
            FreeContactQueryResponse.MessageBody messageBody = freeContactQueryResponse.getMessageBody();
            JsonElement jsonElement = messageBody.getGroupObj();
            if (jsonElement.isJsonArray()) {
                Type type = new TypeToken<List<FreeContactQueryResponse.MemberList>>(){}.getType();
                messageBody.setMemberLists(gson.fromJson(jsonElement, type));
            } else if (jsonElement.isJsonObject()) {
                messageBody.setGroupItem(gson.fromJson(jsonElement, FreeContactQueryResponse.GroupItem.class));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return freeContactQueryResponse;
    }
}

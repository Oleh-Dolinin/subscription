package com.example.subscription.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

@JsonComponent
public class UserAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("name", user.getName());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("locale", user.getLocale());
        jsonObject.addProperty("lastVisit", user.getLastVisit().toString());
        jsonObject.addProperty("goods", String.valueOf(user.getGoods().stream()));

        return jsonObject;
    }
}

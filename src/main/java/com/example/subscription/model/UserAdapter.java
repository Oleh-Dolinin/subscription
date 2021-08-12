package com.example.subscription.model;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.lang.reflect.Type;
import java.util.List;

@JsonComponent
public class UserAdapter implements JsonSerializer<User> {

    @Autowired
    private NewsAdapter newsAdapter;

    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("name", user.getName());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("locale", user.getLocale());

        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, newsAdapter).create();
        String jsonString =  gson.toJson(user.getGoods());
        jsonObject.addProperty("goods",  jsonString );

        return jsonObject;
    }
}

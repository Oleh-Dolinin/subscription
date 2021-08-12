package com.example.subscription.model;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.lang.reflect.Type;

@JsonComponent
public class NewsAdapter implements JsonSerializer<News>  {

    @Autowired
    private UserAdapter userAdapter;

    @Override
    public JsonElement serialize(News news, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", news.getId());
        jsonObject.addProperty("title", news.getTitle());
        jsonObject.addProperty("description", news.getDescription());

        Gson gson = new GsonBuilder().registerTypeAdapter(User.class, userAdapter).create();
        String jsonString =  gson.toJson( news.getAuthor() );
        jsonObject.addProperty("author",  jsonString );

        return jsonObject;
    }
}

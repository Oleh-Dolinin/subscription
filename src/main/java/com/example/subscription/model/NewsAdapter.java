package com.example.subscription.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.lang.reflect.Type;

@JsonComponent
public class NewsAdapter implements JsonSerializer<News>  {

    @Override
    public JsonElement serialize(News news, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", news.getId());
        jsonObject.addProperty("title", news.getTitle());
        jsonObject.addProperty("description", news.getDescription());
        jsonObject.addProperty("localDateTime", news.getLocalDateTime().toString());
        jsonObject.addProperty("author", news.getAuthor().getId());

        return jsonObject;
    }
}

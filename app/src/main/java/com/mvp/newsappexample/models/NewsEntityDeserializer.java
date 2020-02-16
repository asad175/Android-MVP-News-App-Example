package com.mvp.newsappexample.models;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

    /*
        In News Entity Object, sometimes multimedia is an Array and sometimes its an empty string, So need to write a custom Deserializer Adapter to populate it
     */
public class NewsEntityDeserializer implements JsonDeserializer<NewsEntity> {

    @Override
    public NewsEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        NewsEntity newsEntity = new Gson().fromJson(json, NewsEntity.class);
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            Gson gson = new Gson();
            newsEntity.setMediaEntityList(gson.fromJson(jsonObject.getAsJsonArray("multimedia"), new TypeToken<List<MediaEntity>>(){}.getType()));
        }catch (Exception e){}

        return newsEntity;
    }
}

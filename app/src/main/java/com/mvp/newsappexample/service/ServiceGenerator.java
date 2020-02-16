package com.mvp.newsappexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Service generator
 */

public class ServiceGenerator {
    private static final String API_BASE_URL = "https://api.myjson.com/";
    private static final int TIME_OUT = 2;
    private static OkHttpClient.Builder httpClient;


    public static <S> S createService(Class<S> serviceClass, Type type, Object typeAdapter) {

        OkHttpClient.Builder httpClient = getHttpClient();
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (type != null && typeAdapter != null) {
            gsonBuilder.registerTypeAdapter(type, typeAdapter); // set custom Deserializer Adapter if required
        }
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


    private static synchronized OkHttpClient.Builder getHttpClient() {
        if (httpClient != null) return httpClient;

        httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(TIME_OUT, TimeUnit.MINUTES);
        httpClient.connectTimeout(TIME_OUT, TimeUnit.MINUTES);
        httpClient.writeTimeout(TIME_OUT, TimeUnit.MINUTES);
        return httpClient;
    }
}

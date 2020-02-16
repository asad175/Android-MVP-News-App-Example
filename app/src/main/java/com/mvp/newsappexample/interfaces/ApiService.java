package com.mvp.newsappexample.interfaces;

import com.mvp.newsappexample.models.NewsEntity;
import com.mvp.newsappexample.service.JsonArrayResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("bins/nl6jh")
    Call<JsonArrayResponse<NewsEntity>> getNews();
}


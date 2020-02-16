package com.mvp.newsappexample.presenters;


import java.util.ArrayList;
import java.util.List;

import com.mvp.newsappexample.interfaces.ApiService;
import com.mvp.newsappexample.interfaces.INewsRowView;
import com.mvp.newsappexample.interfaces.NewsContract;
import com.mvp.newsappexample.models.MediaEntity;
import com.mvp.newsappexample.models.NewsEntity;
import com.mvp.newsappexample.models.NewsEntityDeserializer;
import com.mvp.newsappexample.models.NewsState;
import com.mvp.newsappexample.service.APIHelper;
import com.mvp.newsappexample.service.JsonArrayResponse;
import com.mvp.newsappexample.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements NewsContract.Presenter  {

    List<NewsEntity> newsList = new ArrayList<>();

    private NewsContract.View view;
    private int lastPosition = 0;
    private ApiService service;
    Call<JsonArrayResponse<NewsEntity>> getNewsCall;

    public MainPresenter() {
        service = ServiceGenerator.createService(ApiService.class, NewsEntity.class, new NewsEntityDeserializer());
    }

    // Subscribe without the state.
    public @Override void subscribe(NewsContract.View view) {
        subscribe(view, null);
    }

    // Subscribe with the provided state.
    public @Override void subscribe(NewsContract.View view, NewsContract.State state) {
        this.view = view;

        // If there are no retrieved news, get them from the API.
        // previously selected position, use 0 as a default one.
        if (state != null && state.getLastNewsList().size() > 0) {
            newsList = state.getLastNewsList();
            lastPosition = state.getLastNewsPosition();
            onNewsPositionChange(lastPosition);
        } else {
            loadNews();
        }

    }

    // Once the state is requested, generate a new state instance.
    public @Override NewsContract.State getState() {
        return new NewsState(newsList, lastPosition);
    }

    // Unsubscribe the view from the presenter.
    public @Override void unsubscribe() {
        APIHelper.cancel(getNewsCall); // Cancel API request to avoid Memory Leak issue
        view = null;
    }

    // Called by the view when the news position change.
    public @Override void onNewsPositionChange(int position) {
        lastPosition = position;
        view.refreshList(); // Refresh it to update selection
        NewsEntity news = newsList.get(position);
        view.setNewsDetail(news.getTitle(),news.getMediaEntityList() != null && news.getMediaEntityList().size() > 0 ? news.getMediaEntityList().get(0).getUrl() : null, news.getSummary(), news.getArticleUrl());
    }

    public void setNewsList(List<NewsEntity> newsList) {
        this.newsList = newsList;
    }

    public void setService(ApiService service) {
        this.service = service;
    }

    // Load News through API
    public void loadNews() {

        view.showLoadingProgress();
        getNewsCall = service.getNews();
        APIHelper.enqueueWith(getNewsCall, new Callback<JsonArrayResponse<NewsEntity>>() {
            @Override
            public void onResponse(Call<JsonArrayResponse<NewsEntity>> call, Response<JsonArrayResponse<NewsEntity>> response) {
                if (view == null) return;
                view.hideLoadingProgress();
                setNewsList(response.body().getResults());
                onNewsPositionChange(lastPosition);
            }

            @Override
            public void onFailure(Call<JsonArrayResponse<NewsEntity>> call, Throwable t) {
                if (view == null) return;
                view.hideLoadingProgress();
                showErrorMessage(t.getMessage());
            }
        });
    }

    void showErrorMessage(String message) {
        view.onError(message);
    }


    // For Adapter news list population
    public void onBindNewsRowViewAtPosition(int position, INewsRowView rowView) {
        NewsEntity newsEntity = newsList.get(position);
        rowView.setTitle(newsEntity.getTitle());
        List<MediaEntity> mediaList = newsEntity.getMediaEntityList();
        rowView.setImage(mediaList != null && mediaList.size() > 0 ? mediaList.get(0).getUrl() : null);
        rowView.setSelection(position == lastPosition, position);
    }

    public int getNewsCount() {
        return newsList.size();
    }

}

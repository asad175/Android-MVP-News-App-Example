package com.mvp.newsappexample.models;

import java.util.List;

import com.mvp.newsappexample.interfaces.NewsContract;

public class NewsState implements NewsContract.State {
    private final List<NewsEntity> newsList;
    private final int newsPosition;

    public NewsState(List<NewsEntity> newsList, int newsPosition) {
        this.newsList = newsList;
        this.newsPosition = newsPosition;
    }

    @Override public List<NewsEntity> getLastNewsList() {
        return newsList;
    }

    @Override public int getLastNewsPosition() {
        return newsPosition;
    }
}

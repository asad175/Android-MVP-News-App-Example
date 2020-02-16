package com.mvp.newsappexample.interfaces;

public interface IView {

    void showLoadingProgress();
    void hideLoadingProgress();
    void onError(String reason);
}
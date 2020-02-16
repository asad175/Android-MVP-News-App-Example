package com.mvp.newsappexample.interfaces;


public interface BasePresenter<V extends com.mvp.newsappexample.interfaces.IView> {
    void subscribe(V view);
    void unsubscribe();
}
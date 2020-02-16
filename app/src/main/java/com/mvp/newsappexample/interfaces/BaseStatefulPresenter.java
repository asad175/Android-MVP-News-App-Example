package com.mvp.newsappexample.interfaces;


interface BaseStatefulPresenter<V extends IView, S> extends BasePresenter<V> {
    void subscribe(V view, S state);
    S getState();
}
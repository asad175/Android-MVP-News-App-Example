package com.mvp.newsappexample.interfaces;

import java.util.List;

import com.mvp.newsappexample.models.NewsEntity;

public interface NewsContract {
    interface View extends IView {
        void refreshList();
        void setNewsDetail(String title, String image, String summary, String url);
    }

    interface State {
        List<NewsEntity> getLastNewsList();
        int getLastNewsPosition();
    }

    interface Presenter extends BaseStatefulPresenter<View, State> {
        void onNewsPositionChange(int position);
    }
}
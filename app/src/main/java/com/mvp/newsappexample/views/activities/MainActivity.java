package com.mvp.newsappexample.views.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import com.mvp.newsappexample.R;
import com.mvp.newsappexample.interfaces.NewsContract;
import com.mvp.newsappexample.models.NewsEntity;
import com.mvp.newsappexample.presenters.MainPresenter;
import com.mvp.newsappexample.views.fragments.NewsDetailFragment;
import com.mvp.newsappexample.views.fragments.NewsListFragment;

public class MainActivity extends BaseActivity implements NewsContract.View {

    NewsListFragment newsListFragment;
    NewsDetailFragment newsDetailFragment;
    View newsDetailPane;
    int screenOrientation;
    MainPresenter presenter = new MainPresenter();
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        this.savedInstanceState = savedInstanceState;
        initializations();


    }

    void initializations() {

        newsDetailPane = findViewById(R.id.news_detail_fragment);
        newsListFragment = (NewsListFragment) getFragmentManager().findFragmentById(R.id.news_list_fragment);
        newsDetailFragment = (NewsDetailFragment)getFragmentManager().findFragmentById(R.id.news_detail_fragment);

        handleScreenOrientation();
    }

    void handleScreenOrientation() {
        screenOrientation = getResources().getConfiguration().orientation;
        toggleNewsDetailPane(screenOrientation != Configuration.ORIENTATION_PORTRAIT);
    }

    private void toggleNewsDetailPane(boolean showDetail) { // detail view will be hidden in portrait mode
        newsDetailPane.setVisibility(showDetail ? View.VISIBLE : View.GONE);
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void onSelectNews(int position) {
        toggleNewsDetailPane(true);
        presenter.onNewsPositionChange(position);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe(this, savedInstanceState != null ? readFromBundle(savedInstanceState) : null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideLoadingProgress(); // cancel progressDialog to avoid leaked window issue
        presenter.unsubscribe(); // Unbind View to avoid Memory Leak issues
    }

    // Interface Methods

    @Override
    public void refreshList() {
        newsListFragment.refreshList();
    }

    @Override
    public void setNewsDetail(String title, String image, String summary, String url) {
        newsDetailFragment.setNewsDetail(title, image, summary, url);
    }

    // Save and restore states on screen orientation change
    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // When saving state, retrieve it from the presenter and save to Bundle.
        outState.putSerializable(getResources().getString(R.string.tag_news_list),(ArrayList)presenter.getState().getLastNewsList());
        outState.putInt(getResources().getString(R.string.tag_news_position), presenter.getState().getLastNewsPosition());
    }

    // Method to convert bundle into State
    NewsContract.State readFromBundle (final Bundle bundle) {
        return new NewsContract.State() {
            @Override
            public List<NewsEntity> getLastNewsList() {
                return (List<NewsEntity>)bundle.getSerializable(getResources().getString(R.string.tag_news_list));
            }

            @Override
            public int getLastNewsPosition() {
                return bundle.getInt(getResources().getString(R.string.tag_news_position));
            }
        };
    }

    // When hardware back button clicked
    @Override
    public void onBackPressed() {
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT && newsDetailPane.getVisibility() == View.VISIBLE) {
            toggleNewsDetailPane(false);
            return;
        }
        finish();
    }
}

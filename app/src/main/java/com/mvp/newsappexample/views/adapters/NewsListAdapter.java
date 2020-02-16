package com.mvp.newsappexample.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mvp.newsappexample.R;
import com.mvp.newsappexample.presenters.MainPresenter;


public class NewsListAdapter extends  RecyclerView.Adapter<NewsViewHolder> {

    private final MainPresenter presenter;
    Context context;

    public NewsListAdapter(Context context, MainPresenter presenter) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(context,LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        presenter.onBindNewsRowViewAtPosition(position, holder);

    }

    @Override
    public int getItemCount() {
        return presenter.getNewsCount();
    }
}
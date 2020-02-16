package com.mvp.newsappexample.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mvp.newsappexample.R;
import com.mvp.newsappexample.views.activities.MainActivity;
import com.mvp.newsappexample.views.adapters.NewsListAdapter;

public class NewsListFragment  extends Fragment {

    RecyclerView list;
    NewsListAdapter adapter;
    View root;
    MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_news_list, container, false);
        initializations();
        return root;
    }

    public void initializations() {
        activity = (MainActivity)getActivity();
        list = root.findViewById(R.id.list);
        adapter = new NewsListAdapter(activity,activity.getPresenter());
        list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        list.setAdapter(adapter);
    }

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

}

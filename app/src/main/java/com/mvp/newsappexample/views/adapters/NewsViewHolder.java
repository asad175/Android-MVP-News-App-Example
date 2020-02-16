package com.mvp.newsappexample.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import androidx.recyclerview.widget.RecyclerView;
import com.mvp.newsappexample.R;
import com.mvp.newsappexample.interfaces.INewsRowView;
import com.mvp.newsappexample.views.activities.MainActivity;

public class NewsViewHolder extends RecyclerView.ViewHolder implements INewsRowView {

    TextView newsTitle;
    DraweeView imageView;
    LinearLayout root;
    Context context;

    public NewsViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        newsTitle = itemView.findViewById(R.id.news_title);
        imageView = itemView.findViewById(R.id.news_item_image);
        root = itemView.findViewById(R.id.layout_root);
    }

    @Override
    public void setTitle(String title) {
        newsTitle.setText(title);
    }

    @Override
    public void setImage(String url) {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(url != null ? ImageRequest.fromUri(Uri.parse(url)) : null).setOldController(imageView.getController()).build();
        imageView.setController(draweeController);
    }

    @Override
    public void setSelection(boolean isSelected, final int position) {
        root.setBackgroundColor(context.getResources().getColor(isSelected ? R.color.grey : R.color.white));
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).onSelectNews(position);
            }
        });
    }
}
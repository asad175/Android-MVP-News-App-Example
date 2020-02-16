package com.mvp.newsappexample.views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mvp.newsappexample.R;

public class NewsDetailFragment extends Fragment implements View.OnClickListener {

    View root;
    TextView title, summaryContent;
    SimpleDraweeView newsImage;
    Button fullStoryLink;
    String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_news_detail, container, false);
        initializations();
        return root;
    }

    void initializations() {
        title = root.findViewById(R.id.title);
        summaryContent = root.findViewById(R.id.summary_content);
        newsImage = root.findViewById(R.id.news_image);
        fullStoryLink = root.findViewById(R.id.full_story_link);
    }

    public void setNewsDetail(String title, String image, String summary, String url) {
        this.title.setText(title);
        this.summaryContent.setText(summary);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(image != null ? ImageRequest.fromUri(Uri.parse(image)) : null).setOldController(newsImage.getController()).build();
        newsImage.setController(draweeController);
        this.url = url;
        this.fullStoryLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        openStoryUrlInBrowser();
    }

    public void openStoryUrlInBrowser() {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }catch (Exception e){}
    }

}


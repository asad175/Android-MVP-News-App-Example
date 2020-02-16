package com.mvp.newsappexample.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("abstract")
    private String summary;
    @SerializedName("url")
    private String articleUrl;
    @SerializedName("byline")
    private String byline;
    @SerializedName("published_date")
    private String publishedDate;
    private List<MediaEntity> mediaEntityList; // Populated through Custom Deserializer as sometimes it can be Array and sometimes Empty String


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public List<MediaEntity> getMediaEntityList() {
        return mediaEntityList;
    }

    public void setMediaEntityList(List<MediaEntity> mediaEntityList) {
        this.mediaEntityList = mediaEntityList;
    }


}

package com.mvp.newsappexample.interfaces;

public interface INewsRowView {
    void setTitle(String title);
    void setImage(String url);
    void setSelection(boolean isSelected, int position);
}

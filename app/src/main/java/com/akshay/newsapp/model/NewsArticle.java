package com.akshay.newsapp.model;

import com.google.gson.annotations.SerializedName;

public class NewsArticle {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("urlToImage")
    private String imageUrl;

    @SerializedName("author")
    private String author;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthor() {
        return author;
    }
}

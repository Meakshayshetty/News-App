package com.akshay.newsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseModel {
    @SerializedName("articles")
    private List<NewsArticle> articles;

    public List<NewsArticle> getArticles() {
        return articles;
    }
}

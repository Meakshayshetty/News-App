package com.akshay.newsapp.network;

import com.akshay.newsapp.model.ApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("top-headlines?country=in&apiKey=88b649f1673c4019b91a0b2da5dc58b6")
    Call<ApiResponseModel> getTopHeadlines();
}

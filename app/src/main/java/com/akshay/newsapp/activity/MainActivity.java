package com.akshay.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akshay.newsapp.R;
import com.akshay.newsapp.adapter.NewsAdapter;
import com.akshay.newsapp.model.ApiResponseModel;
import com.akshay.newsapp.model.NewsArticle;
import com.akshay.newsapp.network.NewsApiService;
import com.akshay.newsapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener{
    private List<NewsArticle> articles;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewHeadLines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callApi();

    }

    private void callApi() {
        NewsApiService newsApiService = RetrofitClient.getRetrofitInstance().create(NewsApiService.class);
        Call<ApiResponseModel> call = newsApiService.getTopHeadlines();
        call.enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {
                if (response.isSuccessful()) {
                    ApiResponseModel apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getArticles() != null) {
                        articles = apiResponse.getArticles();
                        NewsAdapter newsAdapter = new NewsAdapter(articles,MainActivity.this);
                        recyclerView.setAdapter(newsAdapter);
                    }
                } else {
                    Log.e("headlines",response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                // Handle network failures here
                Log.e("headlines",t.getMessage());

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < articles.size()) {
            NewsArticle clickedArticle = articles.get(position);
            // Open the ArticleActivity and pass the clicked article position
            Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
            intent.putExtra("POSITION", position);
            intent.putExtra("ARTICLE_TITLE", clickedArticle.getTitle());
            intent.putExtra("ARTICLE_DESCRIPTION", clickedArticle.getDescription());
            intent.putExtra("ARTICLE_IMAGE", clickedArticle.getImageUrl());
            intent.putExtra("ARTICLE_AUTHOR",clickedArticle.getAuthor());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid article selected", Toast.LENGTH_SHORT).show();
        }
    }
}
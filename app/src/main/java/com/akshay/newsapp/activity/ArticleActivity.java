package com.akshay.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.akshay.newsapp.R;
import com.akshay.newsapp.model.NewsArticle;
import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private ImageView imageViewBanner;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private List<NewsArticle> articles;
    private TextView textViewAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        imageViewBanner = findViewById(R.id.imageViewBanner);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewAuthor = findViewById(R.id.article_author);
        Intent intent = getIntent();

        int position = intent.getIntExtra("POSITION", -1);
        articles = intent.getParcelableExtra("ARTICLE_LIST");
        String title = intent.getStringExtra("ARTICLE_TITLE");
        String description = intent.getStringExtra("ARTICLE_DESCRIPTION");
        String imageUrl = intent.getStringExtra("ARTICLE_IMAGE");
        String author = intent.getStringExtra("ARTICLE_AUTHOR");

        if (position != -1) {
            Glide.with(this).load(imageUrl).into(imageViewBanner);

            textViewTitle.setText(title);
            textViewDescription.setText(description);
            textViewAuthor.setText(author);

           // NewsArticle article = articles.get(position);
           // displayArticle(article);
        } else {
            // Handle invalid article selection
            Toast.makeText(this,"No article",Toast.LENGTH_SHORT).show();
        }
    }

 /*   private void displayArticle(NewsArticle article) {
        Glide.with(this).load(article.getImageUrl()).into(imageViewBanner);

        textViewTitle.setText(article.getTitle());
        textViewDescription.setText(article.getDescription());
    }*/
}
package com.ggproject.gustavo.moviefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private final boolean UP_BUTTONS_STATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        showToolbar("",UP_BUTTONS_STATE);

        ImageView imgHeaderMovieDetail = (ImageView) findViewById(R.id.imageHeaderMovieDetail);

        MovieFeed movieData = (MovieFeed) getIntent().getSerializableExtra("movieFeedInfo");
        buildHeaderImg(movieData,imgHeaderMovieDetail);

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void buildHeaderImg(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){
        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);
    }

}

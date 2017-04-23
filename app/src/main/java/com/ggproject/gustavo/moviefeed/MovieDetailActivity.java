package com.ggproject.gustavo.moviefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.model.Rating;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private final boolean UP_BUTTONS_STATE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        showToolbar("",UP_BUTTONS_STATE);

        ImageView imgHeaderMovieDetail = (ImageView) findViewById(R.id.imageHeaderMovieDetail);

        MovieFeed movieData = (MovieFeed) getIntent().getSerializableExtra("movieFeedInfo");
        buildMovieDetails(movieData,imgHeaderMovieDetail);

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void buildMovieDetails(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){

        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);

        TextView movieTitle = (TextView) findViewById(R.id.movieDetailsMovieTitle);
        movieTitle.setText(movieFeed.getTitle());

        TextView imdbRating = (TextView) findViewById(R.id.imdBRatingMovieDetail);
        imdbRating.setText(movieFeed.getImdbRating());

        TextView rottenTomatoes = (TextView) findViewById(R.id.rottenTomatoesDetail);
        Rating ratingTomatoes = movieFeed.getRatings().get(1);
        rottenTomatoes.setText(ratingTomatoes.getValue());

        TextView metacriticRating = (TextView) findViewById(R.id.metascoreMovieDetail);
        Rating ratingMetacritic = movieFeed.getRatings().get(2);
        metacriticRating.setText(ratingMetacritic.getValue());

        TextView plot = (TextView) findViewById(R.id.plotMovieDetail);
        plot.setText(movieFeed.getPlot());
    }

}

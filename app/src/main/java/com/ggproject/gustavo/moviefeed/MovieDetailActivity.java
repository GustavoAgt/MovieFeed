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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        showToolbar("", false);

        ImageView imgHeaderMovieDetail = (ImageView) findViewById(R.id.imageHeaderMovieDetail);

        MovieFeed movieData = (MovieFeed) getIntent().getSerializableExtra("movieFeedInfo");
        buildMovieDetails(movieData,imgHeaderMovieDetail);

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMovieDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void buildMovieDetails(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){

        final int IMDB_RATING_LIST_POSITION = 0,
                  ROTTENTOMATOES_RATING_LIST_POSITION = 1,
                  METACRITICS_RATING_LIST_POSITION = 2;

        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);

        TextView movieTitle = (TextView) findViewById(R.id.movieDetailsMovieTitle);
        movieTitle.setText(movieFeed.getTitle());

        TextView imdbRating = (TextView) findViewById(R.id.imdBRatingMovieDetail);
        imdbRating.setText(movieFeed.getRatings().get(IMDB_RATING_LIST_POSITION).getValue());

        TextView rottenTomatoes = (TextView) findViewById(R.id.rottenTomatoesDetail);
        rottenTomatoes.setText(movieFeed.getRatings().get(ROTTENTOMATOES_RATING_LIST_POSITION).getValue());

        TextView metacriticRating = (TextView) findViewById(R.id.metascoreMovieDetail);
        metacriticRating.setText(movieFeed.getRatings().get(METACRITICS_RATING_LIST_POSITION).getValue());

        TextView plot = (TextView) findViewById(R.id.plotMovieDetail);
        plot.setText(movieFeed.getPlot());
    }

}

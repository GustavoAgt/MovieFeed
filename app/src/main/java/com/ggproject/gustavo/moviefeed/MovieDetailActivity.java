package com.ggproject.gustavo.moviefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.squareup.picasso.Picasso;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        showToolbar("", false);

        ImageView imgHeaderMovieDetail = (ImageView) findViewById(R.id.imageHeaderMovieDetail);

        MovieFeed movieData = (MovieFeed) getIntent().getSerializableExtra("movieFeedInfo");

        buildPlotAndImg(movieData, imgHeaderMovieDetail);
        buildIMDBProgressBar(movieData);
        buildRottenTomatoesProgressBar(movieData);
        buildMetacriticProgressBar(movieData);

    }

    private void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMovieDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    private void buildIMDBProgressBar(MovieFeed movieFeed){

        final int IMDB_RATING_LIST_POSITION = 0;

        String ratingValueOf100Percent = movieFeed.getRatings().get(IMDB_RATING_LIST_POSITION).getValue();

        Double iMDBdoubleValue = ((10) * Double.parseDouble(ratingValueOf100Percent.substring(0,3)));

        int imdbIntValue = iMDBdoubleValue.intValue();

        RingProgressBar mRingProgressBar = (RingProgressBar) findViewById(R.id.imdbProgressbar);

        mRingProgressBar.setProgress(imdbIntValue);

    }

    private void buildRottenTomatoesProgressBar(MovieFeed movieFeed){

        final int ROTTENTOMATOES_RATING_LIST_POSITION = 1;

        String ratingValueOf100Percent = movieFeed.getRatings().get(ROTTENTOMATOES_RATING_LIST_POSITION).getValue();

        int rottenTomatoesProgressBarIntValue = Integer.parseInt(ratingValueOf100Percent.substring(0,2));

        RingProgressBar mRingProgressBar = (RingProgressBar) findViewById(R.id.rottenTomatoesProgressbar);
        mRingProgressBar.setProgress(rottenTomatoesProgressBarIntValue);

    }

    private void buildMetacriticProgressBar(MovieFeed movieFeed){

        final int METACRITICS_RATING_LIST_POSITION = 2;

        movieFeed.getRatings().get(METACRITICS_RATING_LIST_POSITION).getValue();

        String ratingValueOf100Percent = movieFeed.getRatings().get(METACRITICS_RATING_LIST_POSITION).getValue();

        int metacriticIntValue = Integer.parseInt(ratingValueOf100Percent.substring(0,2));

        RingProgressBar mRingProgressBar = (RingProgressBar) findViewById(R.id.metacriticsProgressbar);
        mRingProgressBar.setProgress(metacriticIntValue);
    }

    private void buildPlotAndImg(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){

        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);

        TextView movieTitle = (TextView) findViewById(R.id.movieDetailsMovieTitle);
        movieTitle.setText(movieFeed.getTitle());

        TextView plot = (TextView) findViewById(R.id.plotMovieDetail);
        plot.setText(movieFeed.getPlot());
    }

}

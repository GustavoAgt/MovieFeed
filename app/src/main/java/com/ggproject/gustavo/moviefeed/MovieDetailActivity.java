package com.ggproject.gustavo.moviefeed;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.model.Rating;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

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

        CircularProgressBar imdbProgressBar = (CircularProgressBar)findViewById(R.id.imdbProgressbar);
        imdbProgressBar.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        imdbProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroudColorPrimaryDark_loginButton));
        imdbProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        imdbProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500;
        imdbProgressBar.setProgressWithAnimation(imdbIntValue, animationDuration);

    }

    private void buildRottenTomatoesProgressBar(MovieFeed movieFeed){

        final int ROTTENTOMATOES_RATING_LIST_POSITION = 1;

        String ratingValueOf100Percent = movieFeed.getRatings().get(ROTTENTOMATOES_RATING_LIST_POSITION).getValue();

        Double rottenTomatoesProgressBardoubleValue = Double.parseDouble(ratingValueOf100Percent.substring(0,2));
        int rottenTomatoesProgressBarIntValue = rottenTomatoesProgressBardoubleValue.intValue();

        CircularProgressBar rottenTomatoesProgressBar = (CircularProgressBar)findViewById(R.id.rottenTomatoesProgressbar);
        rottenTomatoesProgressBar.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        rottenTomatoesProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroudColorPrimaryDark_loginButton));
        rottenTomatoesProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        rottenTomatoesProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500;
        rottenTomatoesProgressBar.setProgressWithAnimation(rottenTomatoesProgressBarIntValue, animationDuration);
    }

    private void buildMetacriticProgressBar(MovieFeed movieFeed){

        final int METACRITICS_RATING_LIST_POSITION = 2;

        movieFeed.getRatings().get(METACRITICS_RATING_LIST_POSITION).getValue();

        String ratingValueOf100Percent = movieFeed.getRatings().get(METACRITICS_RATING_LIST_POSITION).getValue();

        Double metacriticDoubleValue = (Double.parseDouble(ratingValueOf100Percent.substring(0,2)));

        int metacriticIntValue = metacriticDoubleValue.intValue();
        System.out.println((10) * Double.parseDouble(ratingValueOf100Percent.substring(0,2)));
        CircularProgressBar metacriticProgressBar = (CircularProgressBar)findViewById(R.id.metacriticsProgressbar);
        metacriticProgressBar.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        metacriticProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroudColorPrimaryDark_loginButton));
        metacriticProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        metacriticProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500;
        metacriticProgressBar.setProgressWithAnimation(metacriticIntValue, animationDuration);

    }

    private void buildPlotAndImg(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){

        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);

        TextView movieTitle = (TextView) findViewById(R.id.movieDetailsMovieTitle);
        movieTitle.setText(movieFeed.getTitle());

        TextView plot = (TextView) findViewById(R.id.plotMovieDetail);
        plot.setText(movieFeed.getPlot());
    }

}

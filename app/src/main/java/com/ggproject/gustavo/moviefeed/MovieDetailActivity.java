package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        showToolbar("", false);

        ImageView imgHeaderMovieDetail = (ImageView) findViewById(R.id.imageHeaderMovieDetail);

        MovieFeed movieData = (MovieFeed) getIntent().getSerializableExtra("movieFeed");

        buildPoster(movieData,imgHeaderMovieDetail);

        buildPlot(movieData);
        buildTitle(movieData);
        buildRutime(movieData);
        buildGenre(movieData);
        buildIMDBProgressBar(movieData);
        buildRottenTomatoesProgressBar(movieData);
        buildMetacriticProgressBar(movieData);
        buildCardViewMoreInfo(movieData);
        clickFabYoutubeTrailer(movieData);
    }

    private void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMovieDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    private void buildIMDBProgressBar(MovieFeed movieFeed){

        final int IMDB_RATING_LIST_POSITION = 0;

        String imdbRatingValue = movieFeed.getRatings().get(IMDB_RATING_LIST_POSITION).getValue();
        TextView imdbTexViewRating = (TextView) findViewById(R.id.imdb_rating);
        imdbTexViewRating.setText(imdbRatingValue);

        Double iMDBdoubleValue = ((10) * Double.parseDouble(imdbRatingValue.substring(0,3)));

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

    private void buildTitle(MovieFeed movieFeed){
        TextView movieTitle = (TextView) findViewById(R.id.movieDetailsMovieTitle);
        movieTitle.setText(movieFeed.getTitle());
    }

    private void buildRutime(MovieFeed movieFeed){
        TextView textViewRuntime = (TextView) findViewById(R.id.runtime);
        textViewRuntime.setText(movieFeed.getRuntime());
    }

    private void buildGenre(MovieFeed movieFeed){
        TextView textviewGenre = (TextView) findViewById(R.id.gerne);
        textviewGenre.setText(movieFeed.getGenre());
    }

    private void buildPlot(MovieFeed movieFeed){
        TextView textViewplot = (TextView) findViewById(R.id.plotMovieDetail);
        textViewplot.setText(movieFeed.getPlot());
    }

    private void buildPoster(MovieFeed movieFeed, ImageView imgHeaderMovieDetail){
        Picasso.with(getBaseContext()).load(movieFeed.getPoster()).into(imgHeaderMovieDetail);
    }


    private void buildCardViewMoreInfo(MovieFeed movieFeed){
        TextView actorsTextView = (TextView) findViewById(R.id.cardview_actors);
        actorsTextView.setText(movieFeed.getActors());
    }

    private void clickFabYoutubeTrailer(final MovieFeed movieFeed){
        FloatingActionButton fabYTt = (FloatingActionButton) findViewById(R.id.floating_action_button_youtube_trailer);

        fabYTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent youtubePlayerIntent = new Intent(
                                                            getApplicationContext(),
                                                            YoutubeTrailerActivity.class
                                                        );

                youtubePlayerIntent.putExtra("movieTitle", movieFeed.getTitle());
                startActivity(youtubePlayerIntent);
            }
        });
    }
}

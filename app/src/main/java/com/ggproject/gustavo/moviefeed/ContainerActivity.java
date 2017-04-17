package com.ggproject.gustavo.moviefeed;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.view.fragments.MovieFragment;

public class ContainerActivity extends AppCompatActivity {

    private MovieFeed movieFeed;
    private TextView titleViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        MovieFragment movieFragment = new MovieFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, movieFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    public MovieFeed getMovieFeed() {
        return movieFeed;
    }

    public void setMovieFeed(MovieFeed movieFeed) {
        this.movieFeed = movieFeed;
    }
}

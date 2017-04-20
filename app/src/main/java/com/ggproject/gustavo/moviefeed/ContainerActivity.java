package com.ggproject.gustavo.moviefeed;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ggproject.gustavo.moviefeed.views.fragments.MovieFragment;

import java.io.Serializable;

public class ContainerActivity extends AppCompatActivity {

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

        Serializable serializableMovieFeedObj = getIntent().getSerializableExtra("movieFeed");

        Bundle bundleMovie = new Bundle();
        bundleMovie.putSerializable("movieFeedInfo",serializableMovieFeedObj);
        movieFragment.setArguments(bundleMovie);
    }
}

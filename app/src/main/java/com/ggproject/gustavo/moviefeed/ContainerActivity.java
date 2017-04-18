package com.ggproject.gustavo.moviefeed;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ggproject.gustavo.moviefeed.view.fragments.MovieFragment;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {

    private ArrayList<String> movieData;

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

        setMovieData(
                getIntent().getStringExtra("titleMovie"),
                getIntent().getStringExtra("yearMovie"),
                getIntent().getStringExtra("genreMovie"),
                getIntent().getStringExtra("ratingIMDBMovie")
        );

        Bundle bundleMovie = new Bundle();
        bundleMovie.putStringArrayList("movieData",getMovieData());
        movieFragment.setArguments(bundleMovie);

    }

    private ArrayList<String> getMovieData() {
        return movieData;
    }

    private void setMovieData(String... movieInfo) {
        movieData = new ArrayList<>();
        for(String data: movieInfo)
            movieData.add(data);
    }
}

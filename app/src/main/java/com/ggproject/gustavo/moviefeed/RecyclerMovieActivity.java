package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.ggproject.gustavo.moviefeed.adapter.MovieInfoRecyclerView;
import com.ggproject.gustavo.moviefeed.model.Movie;
public class RecyclerMovieActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_movie);

        final Movie movieData = (Movie) getIntent().getSerializableExtra("movie");

        MovieInfoRecyclerView movieRecycler = new MovieInfoRecyclerView(
                movieData.getSearch(),
                R.layout.cardview_movie,
                this
        );

        initializeRecyclerView();
        moviesRecyclerView.setAdapter(movieRecycler);
    }

    public void initializeRecyclerView(){

        moviesRecyclerView = (RecyclerView) findViewById(R.id.previewMovieInfoRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        moviesRecyclerView.setLayoutManager(linearLayoutManager);

    }

}

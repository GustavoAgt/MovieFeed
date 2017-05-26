package com.ggproject.gustavo.moviefeed.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.MovieDetailActivity;
import com.ggproject.gustavo.moviefeed.R;
import com.ggproject.gustavo.moviefeed.adapter.MovieInfoRecyclerView;
import com.ggproject.gustavo.moviefeed.model.Movie;

import com.ggproject.gustavo.moviefeed.model.Search;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MovieFragment extends Fragment {

    public MovieFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        showToolBar("", true, view);

        RecyclerView moviesRecyclerView = (RecyclerView) view.findViewById(R.id.previewMovieInfoRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        moviesRecyclerView.setLayoutManager(linearLayoutManager);

        final Movie movieData = (Movie) getArguments().getSerializable("movieFeedInfo");

        MovieInfoRecyclerView movieRecycler = new MovieInfoRecyclerView(
            movieData.getSearch(),
            R.layout.cardview_movie,
            getActivity()
        );

        moviesRecyclerView.setAdapter(movieRecycler);

        return view;
    }
    public void showToolBar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
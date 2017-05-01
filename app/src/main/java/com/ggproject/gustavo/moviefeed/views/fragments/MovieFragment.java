package com.ggproject.gustavo.moviefeed.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.MovieDetailActivity;
import com.ggproject.gustavo.moviefeed.R;
import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
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
        showToolBar(getResources().getString(R.string.toolbarMovieTitle), true, view);

        final MovieFeed movieData = (MovieFeed) getArguments().getSerializable("movieFeedInfo");

        setCardViewInformation(view, movieData);

        ImageView imageView = (ImageView) view.findViewById(R.id.pictureCard);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movieFeedInfo", movieData);
                startActivity(intent);
            }

        });

        return view;
    }

    public void setCardViewInformation(View view, MovieFeed movieData){

        final int IMDB_RATING_LIST_POSITION = 0;

        TextView textTitle = (TextView) view.findViewById(R.id.titleCardviewMovie);
        textTitle.setText(movieData.getTitle());

        TextView textYear = (TextView) view.findViewById(R.id.yearCardView);
        textYear.setText(movieData.getYear());

        TextView textGenre = (TextView) view.findViewById(R.id.genreCardView);
        textGenre.setText(movieData.getGenre());

        TextView textRatingIMDB = (TextView) view.findViewById(R.id.imdBRating);
        textRatingIMDB.setText(movieData.getRatings().get(IMDB_RATING_LIST_POSITION).getValue());

        ImageView pictureCard = (ImageView) view.findViewById(R.id.pictureCard);
        Picasso.with(getActivity()).load(movieData.getPoster()).into(pictureCard);
    }

    public void showToolBar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}

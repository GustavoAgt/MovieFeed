package com.ggproject.gustavo.moviefeed.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.R;

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
        setCardViewInformation(view);
        return view;
    }

    public void setCardViewInformation(View view){

        ArrayList<String> movieData = getArguments().getStringArrayList("movieData");

        TextView textTitle = (TextView) view.findViewById(R.id.titleCardviewMovie);
        textTitle.setText(movieData.get(0));

        TextView textYear = (TextView) view.findViewById(R.id.yearCardView);
        textYear.setText(movieData.get(1));

        TextView textGenre = (TextView) view.findViewById(R.id.genreCardView);
        textGenre.setText(movieData.get(2));

        TextView textRatingIMDB = (TextView) view.findViewById(R.id.imdBRating);
        textRatingIMDB.setText(movieData.get(3));
    }
}

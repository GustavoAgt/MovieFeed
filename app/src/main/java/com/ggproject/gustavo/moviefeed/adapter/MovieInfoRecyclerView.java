package com.ggproject.gustavo.moviefeed.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggproject.gustavo.moviefeed.R;
import com.ggproject.gustavo.moviefeed.model.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gustavo on 5/26/2017.
 */

public class MovieInfoRecyclerView extends RecyclerView.Adapter<MovieInfoRecyclerView.MovieViewHolder> {

    private List<Search> searches;
    private int resource;
    private Activity activity;

    public MovieInfoRecyclerView(List<Search> searches, int resource, Activity activity) {
        this.searches = searches;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Search search = searches.get(position);

        holder.movieTitle.setText(search.getTitle());
        holder.movieYear.setText(search.getYear());
        Picasso.with(activity).load(search.getPoster()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private TextView movieYear;
        private ImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieTitle  = (TextView)    itemView.findViewById(R.id.titleCardviewMovie);
            movieYear   = (TextView)    itemView.findViewById(R.id.yearCardView);
            moviePoster = (ImageView)   itemView.findViewById(R.id.pictureCard);
        }
    }
}

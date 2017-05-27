package com.ggproject.gustavo.moviefeed.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ggproject.gustavo.moviefeed.MovieDetailActivity;
import com.ggproject.gustavo.moviefeed.R;
import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.model.Search;
import com.ggproject.gustavo.moviefeed.propertycontainer.StaticContainer;
import com.ggproject.gustavo.moviefeed.restclient.MovieFeedRestClient;
import com.ggproject.gustavo.moviefeed.views.fragments.Loader;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gustavo on 5/26/2017.
 */

public class MovieInfoRecyclerView extends RecyclerView.Adapter<MovieInfoRecyclerView.MovieViewHolder> {

    private List<Search> searches;
    private int resource;
    private Activity activity;
    private Gson gson;
    private Retrofit retrofit;
    private MovieFeedRestClient movieFeedRestClient;

    public MovieInfoRecyclerView(List<Search> searches, int resource, Activity activity) {
        this.searches = searches;
        this.resource = resource;
        this.activity = activity;

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieFeedRestClient = retrofit.create(MovieFeedRestClient.class);

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Search search = searches.get(position);

        holder.movieTitle.setText(search.getTitle());
        holder.movieYear.setText(search.getYear());
        Picasso.with(activity).load(search.getPoster()).into(holder.moviePoster);

        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MovieFeed> call = movieFeedRestClient.getData(search.getImdbID(), "full", StaticContainer.getApiKey());

                call.enqueue(new Callback<MovieFeed>() {
                    @Override
                    public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                        switch (response.code()){
                            case 200:
                                if(response.body().getResponse().toLowerCase().equals("false")){

                                    break;
                                }

                                startActivityMovieDetails();
                            case 400:

                                break;
                            default:
                                Log.d("MovieInfoRecyclerView", "" +response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieFeed> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;
        private TextView movieYear;
        private ImageView moviePoster;

        public MovieViewHolder(final View itemView) {
            super(itemView);
            movieTitle  = (TextView)    itemView.findViewById(R.id.titleCardviewMovie);
            movieYear   = (TextView)    itemView.findViewById(R.id.yearCardView);
            moviePoster = (ImageView)   itemView.findViewById(R.id.pictureCard);
        }
    }

    public void startActivityMovieDetails(){
        Intent intentMovieDetail = new Intent(this.activity, MovieDetailActivity.class);
        activity.startActivity(intentMovieDetail);
    }
}

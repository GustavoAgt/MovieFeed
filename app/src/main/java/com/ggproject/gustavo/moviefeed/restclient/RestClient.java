package com.ggproject.gustavo.moviefeed.restclient;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Gustavo on 4/14/2017.
 */

public interface RestClient {
    @GET("movie")
    Call<MovieFeed> getData();
}

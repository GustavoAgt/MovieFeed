package com.ggproject.gustavo.moviefeed.restclient;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gustavo on 4/14/2017.
 */

public interface MovieFeedRestClient {

    @GET("/")
    Call<MovieFeed> getData(
            @Query("i") String imdbID,
            @Query("plot") String plot,
            @Query("apikey") String key
    );
}

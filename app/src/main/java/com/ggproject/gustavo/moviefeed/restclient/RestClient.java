package com.ggproject.gustavo.moviefeed.restclient;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gustavo on 4/14/2017.
 */

public interface RestClient {

    @GET("/")
    Call<MovieFeed> getData(
            @Query("t") String movieParam,
            @Query("plot") String plot
    );
}

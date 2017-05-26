package com.ggproject.gustavo.moviefeed.restclient;

import com.ggproject.gustavo.moviefeed.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gustavo on 5/23/2017.
 */

public interface MovieRestClient {
    @GET("/")
    Call<Movie> getData(
            @Query("s")         String searchParam,
            @Query("t")         String contentType,
            @Query("apikey")    String apiKey
    );
}

package com.ggproject.gustavo.moviefeed.restclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.ggproject.gustavo.moviefeed.model.youtubetrailer.MovieTrailerYoutubeInfo;
/**
 * Created by ggt_0 on 4/6/2017.
 */

public interface YoutubeMovieTrailerRestClient {
    @GET("/youtube/v3/search")
    Call<MovieTrailerYoutubeInfo> getData(
            @Query("maxResults") int maxResults,
            @Query("part") String part,
            @Query("q") String movieToSearch,
            @Query("key") String key
        );
}

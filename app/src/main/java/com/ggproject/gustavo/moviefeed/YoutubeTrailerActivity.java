package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ggproject.gustavo.moviefeed.model.youtubetrailer.MovieTrailerYoutubeInfo;
import com.ggproject.gustavo.moviefeed.restclient.YoutubeMovieTrailerRestClient;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeTrailerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private final String YOUTUBE_KEY = "AIzaSyBf6cNTx8DaQjHP4dzjTF5LUOPRGElMudk";
    private final int MAX_RESULTS = 1;
    private final String PART = "id";
    private YouTubePlayerView youtubePlayerView;
    private Gson gson;
    private Retrofit retrofit;
    private YoutubeMovieTrailerRestClient ytMovieTrailer;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_trailer);

        String movieTitle = getIntent().getStringExtra("movieTitle");

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ytMovieTrailer = retrofit.create(YoutubeMovieTrailerRestClient.class);

        youtubePlayerView= (YouTubePlayerView) findViewById(R.id.youtubeView);
        youtubePlayerView.initialize(YOUTUBE_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean restored) {
        if(!restored){

            String movieTitle = getIntent().getStringExtra("movieTitle");

            Call<MovieTrailerYoutubeInfo> call = ytMovieTrailer.getData(MAX_RESULTS, PART, movieTitle +" official trailer", YOUTUBE_KEY);

            call.enqueue(new Callback<MovieTrailerYoutubeInfo>() {
                @Override
                public void onResponse(Call<MovieTrailerYoutubeInfo> call, Response<MovieTrailerYoutubeInfo> response) {
                    switch (response.code()){
                        case 200:
                            videoId = response.body().getItems().get(0).getId().getVideoId();
                            youTubePlayer.cueVideo(videoId);
                            Log.d("YoutubeTrailerActivity", "" +response.code());
                            break;
                        case 400:
                            Log.d("YoutubeTrailerActivity", "" +response.code());
                            break;
                        default:
                            Log.d("YoutubeTrailerActivity", "" +response.code());
                            Log.d("YoutubeTrailerActivity", "" +call.request());
                            Log.d("YoutubeTrailerActivity", "" +response.message());
                    }
                }

                @Override
                public void onFailure(Call<MovieTrailerYoutubeInfo> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        else {
            Log.d("YoutubeTrailerActivity", youTubeInitializationResult.toString());
            Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==1){
            getYoutubePlayerProvider().initialize(YOUTUBE_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return youtubePlayerView;
    }
}

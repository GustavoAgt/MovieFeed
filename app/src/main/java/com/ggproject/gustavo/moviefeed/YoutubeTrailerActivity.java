package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeTrailerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private final String YOUTUBE_PASSWORD = "AIzaSyBf6cNTx8DaQjHP4dzjTF5LUOPRGElMudk";
    private YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_trailer);

        youtubePlayerView= (YouTubePlayerView) findViewById(R.id.youtubeView);
        youtubePlayerView.initialize(YOUTUBE_PASSWORD, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        if(!restored)
            youTubePlayer.cueVideo("HIsacL-E_gA");
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
            getYoutubePlayerProvider().initialize(YOUTUBE_PASSWORD, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return youtubePlayerView;
    }
}

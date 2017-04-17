package com.ggproject.gustavo.moviefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.restclient.RestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchMovie();
    }

    public void searchMovie(){

        Button searchMovieButton = (Button) findViewById(R.id.searchButton);

        searchMovieButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadMovieInformation();
            }
        });
    }

    public void loadMovieInformation(){

        Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.omdbapi.com/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        RestClient restClient = retrofit.create(RestClient.class);

        Call<MovieFeed> call = restClient.getData("titanic","full");

        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                switch(response.code()){
                    case 200:
                        System.out.println(call.request());
                        MovieFeed movie = response.body();
                        System.out.println(movie.getTitle());
                        System.out.println(response.message());
                        break;
                    case 400:
                        Log.println(Log.INFO,"Success", "400 status");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
                Log.println(Log.INFO,"Fail", t.getMessage());
            }
        });
    }
}

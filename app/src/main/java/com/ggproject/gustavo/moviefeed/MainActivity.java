package com.ggproject.gustavo.moviefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.restclient.RestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }

    public void loadMovieInformation(){
        Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.omdbapi.com/?t=titanic&plot=full")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        RestClient restClient = retrofit.create(RestClient.class);

        Call<MovieFeed> call = restClient.getData();

        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {

            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {

            }
        });
    }
}

package com.ggproject.gustavo.moviefeed;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .serializeNulls()
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
                switch (response.code()){
                    case 200:
                        MovieFeed movieFeed = response.body();
                        startContainerActivity();
                        setCardView(movieFeed);
                        break;
                    case 400:
                        System.out.println("Bad request");
                        break;
                    default:
                        System.out.println("Issue no identified");
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {

            }
        });
    }

    private void startContainerActivity(){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    private void setCardView(MovieFeed movieFeed){
        Activity activity = getParent();

        TextView title = (TextView) activity.findViewById(R.id.titleCardviewMovie);
        title.setText(movieFeed.getTitle());

        TextView year = (TextView) activity.findViewById(R.id.yearCardView);
        year.setText(movieFeed.getYear());

        TextView genre = (TextView) activity.findViewById(R.id.genreCardView);
        genre.setText(movieFeed.getGenre());

        TextView imdbRating = (TextView) activity.findViewById(R.id.imdBRating);
        imdbRating.setText(movieFeed.getImdbRating());

    }
}

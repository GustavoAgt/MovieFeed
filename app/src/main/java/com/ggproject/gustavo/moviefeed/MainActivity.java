package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggproject.gustavo.moviefeed.model.Movie;
import com.ggproject.gustavo.moviefeed.model.MovieFeed;
import com.ggproject.gustavo.moviefeed.model.Search;
import com.ggproject.gustavo.moviefeed.restclient.MovieFeedRestClient;

import com.ggproject.gustavo.moviefeed.restclient.MovieRestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "767b7407";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchMovie();
    }

    public void searchMovie(){

        Button searchMovieButton = (Button) findViewById(R.id.searchButtonDashBoard);

        searchMovieButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText movieEditText = (EditText) findViewById(R.id.movieSearchTextInput);

                if (movieEditText.getText().toString().equals(""))
                    Toast.makeText(getBaseContext(), "Please add a valid movie", Toast.LENGTH_LONG).show();
                else
                    loadMovieInformation(movieEditText.getText().toString());
            }
        });
    }

    public void loadMovieInformation(String movie){

        Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .serializeNulls()
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.omdbapi.com/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        MovieRestClient restClient = retrofit.create(MovieRestClient.class);

        Call<Movie> call = restClient.getData(movie, API_KEY);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                switch (response.code()){
                    case 200:
                        if(response.body().getResponse().toLowerCase().equals("false")){
                            Toast.makeText(getBaseContext(), "Movie not found", Toast.LENGTH_LONG).show();
                            break;
                        }

                        for(Search search: response.body().getSearch()){
                            Log.d("Main Activity", search.getTitle());
                        }

                        //startContainerActivity(response);
                        break;
                    case 400:
                        Toast.makeText(getBaseContext(),"Something gone wrong 400", Toast.LENGTH_LONG).show();
                        break;
                    default:
                       Log.d("Main Activity", "" +response.code());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getBaseContext(),"No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startContainerActivity(Response<MovieFeed> res){
        Intent intent = new Intent(this, ContainerActivity.class);
        setIntentValues(intent,res.body());
        startActivity(intent);
    }

    private void setIntentValues(Intent intent, MovieFeed movieFeed){
        intent.putExtra("movieFeed", movieFeed);
    }
}

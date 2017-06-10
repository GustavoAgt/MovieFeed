package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggproject.gustavo.moviefeed.model.Movie;

import com.ggproject.gustavo.moviefeed.propertycontainer.StaticContainer;
import com.ggproject.gustavo.moviefeed.restclient.MovieRestClient;
import com.ggproject.gustavo.moviefeed.views.fragments.Loader;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String CONTENT_TYPE = "movie";

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

    public void loadMovieInformation(String title){

        Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .serializeNulls()
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.omdbapi.com/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        MovieRestClient restClient = retrofit.create(MovieRestClient.class);

        Loader loader = new Loader();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, loader)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();


        Call<Movie> call = restClient.getData(title, CONTENT_TYPE, StaticContainer.getApiKey());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                switch (response.code()){
                    case 200:
                        if(response.body().getResponse().toLowerCase().equals("false")){
                            Toast.makeText(getBaseContext(), "Movie not found", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            break;
                        }

                        startContainerActivity(response);
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

    private void startContainerActivity(Response<Movie> res){
        Intent intent = new Intent(this, RecyclerMovieActivity.class);
        setIntentValues(intent,res.body());

        startActivity(intent);
    }

    private void setIntentValues(Intent intent, Movie movie){
        intent.putExtra("movie", movie);
    }
}

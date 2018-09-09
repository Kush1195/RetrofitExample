package com.example.kushal.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import okhttp3.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Obtain Your API_KEY First", Toast.LENGTH_SHORT).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

        retrofit2.Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, retrofit2.Response<MovieResponse> response) {

                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, MainActivity.this));
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
            }
        });

    }
}

    // Retrofit will download and parse the API data on a background thread
   // and then return the results back to the UI thread via the onResponse or onFailure method.
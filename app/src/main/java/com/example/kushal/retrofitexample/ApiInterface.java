package com.example.kushal.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    // The endpoints are defined inside of an interface using special retrofit annotations to encode details about the parameters
    // and request method. In addition, the return value is always a parameterized Call<T> object such as Call<MovieResponse>.

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}

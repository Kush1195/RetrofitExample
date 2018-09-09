package com.example.kushal.retrofitexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    // To send network requests to an API, we need to use the Retrofit Builder class and specify the base URL for the service.
    // So, create a class named ApiClient.java

    public static final String URL = "http://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

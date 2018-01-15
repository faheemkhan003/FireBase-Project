package com.example.fahee.chatapplication.api;

import com.example.fahee.chatapplication.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fahee on 1/15/2018.
 */

public interface Service {
    @GET("movie/popular")
    Call<MoviesResponse>getPopularMovies(@Query("api_key")String apikey);

    @GET("movie/top_rated")
    Call<MoviesResponse>getTopRatedMovies(@Query("api_key")String apikey);
}

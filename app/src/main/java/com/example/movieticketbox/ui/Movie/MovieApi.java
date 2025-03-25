package com.example.movieticketbox.ui.Movie;


import com.example.movieticketbox.ui.Movie.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movies") // Thay thế URL endpoint phù hợp với API backend của bạn
    Call<MovieResponse> getMovies();
}
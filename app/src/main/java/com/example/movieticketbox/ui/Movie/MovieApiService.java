package com.example.movieticketbox.ui.Movie;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiService {
    private static final String BASE_URL = "https://46e3-42-112-78-218.ngrok-free.app/api/"; // Thay bằng URL API thực tế
    private static Retrofit retrofit = null;

    public static MovieApi getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieApi.class);
    }
}
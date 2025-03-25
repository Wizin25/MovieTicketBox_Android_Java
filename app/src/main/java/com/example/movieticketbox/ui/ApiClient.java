package com.example.movieticketbox.ui;

import com.example.movieticketbox.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://46e3-42-112-78-218.ngrok-free.app/"; // URL API
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // Lấy API service
    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }
}
package com.example.movieticketbox.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.*;
import java.io.IOException;

public class MovieSelectionActivity extends AppCompatActivity {

    private LinearLayout movieListLayout;
    private final OkHttpClient client = new OkHttpClient();
    private final String MOVIE_API_URL = "https://46e3-42-112-78-218.ngrok-free.app/api/movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_selection);

        movieListLayout = findViewById(R.id.movieListLayout);
        fetchMovies();
    }

    private void fetchMovies() {
        Request request = new Request.Builder()
                .url(MOVIE_API_URL)
                .get()
                .addHeader("accept", "text/plain")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) return;
                String responseBody = response.body().string();

                runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(responseBody);
                        JSONArray data = json.getJSONArray("data");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject movie = data.getJSONObject(i);
                            int movieId = movie.getInt("id");
                            String movieName = movie.getString("name");

                            Button movieButton = new Button(MovieSelectionActivity.this);
                            movieButton.setText(movieName);
                            movieButton.setOnClickListener(v -> {
                                Intent intent = new Intent(MovieSelectionActivity.this, ShowtimeSelectionActivity.class);
                                intent.putExtra("movieId", movieId);
                                startActivity(intent);
                            });


                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(16, 16, 16, 16);
                            movieButton.setLayoutParams(params);

                            movieListLayout.addView(movieButton);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}

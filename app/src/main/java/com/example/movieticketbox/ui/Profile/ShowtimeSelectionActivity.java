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

import java.io.IOException;

import okhttp3.*;

public class ShowtimeSelectionActivity extends AppCompatActivity {

    private LinearLayout showtimeLayout;
    private final OkHttpClient client = new OkHttpClient();
    private int movieId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime_selection);

        showtimeLayout = findViewById(R.id.showtimeLayout);
        movieId = getIntent().getIntExtra("movieId", -1);
        Log.d("DEBUG", "movieId = " + movieId); // 👈 log kiểm tra truyền dữ liệu

        fetchShowtimes(movieId);
    }

    private void fetchShowtimes(int movieId) {
        String url = "https://46e3-42-112-78-218.ngrok-free.app/api/showtimes/movieid/" + movieId;

        Request request = new Request.Builder()
                .url(url)
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
                Log.d("DEBUG", "RESPONSE = " + responseBody); // 👈 log kiểm tra phản hồi

                runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(responseBody);
                        JSONArray data = json.getJSONArray("data");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject showtime = data.getJSONObject(i);
                            int showtimeId = showtime.getInt("id");
                            String showDateTime = showtime.getString("showDateTime");
                            String roomName = showtime.getString("roomName");

                            Button button = new Button(ShowtimeSelectionActivity.this);
                            button.setText(showDateTime + " - " + roomName);
                            button.setTextColor(0xFFFFFFFF); // màu chữ trắng
                            button.setBackgroundColor(0xFF3F51B5); // nền xanh đậm

                            button.setOnClickListener(v -> {
                                Intent intent = new Intent(ShowtimeSelectionActivity.this, SelectSeatActivity.class);
                                intent.putExtra("movieId", movieId);
                                intent.putExtra("showtimeId", showtimeId);
                                startActivity(intent);
                            });

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            );
                            params.setMargins(16, 16, 16, 16);
                            button.setLayoutParams(params);

                            showtimeLayout.addView(button);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}

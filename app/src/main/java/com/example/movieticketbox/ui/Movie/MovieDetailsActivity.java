package com.example.movieticketbox.ui.Movie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.movieticketbox.R;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imgMovieBackground;
    private TextView txtMovieTitle, txtMovieDirector, txtMovieDuration, txtMovieGenre, txtMovieDescription;
    private Button btnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imgMovieBackground = findViewById(R.id.imgMovieBackground);
        txtMovieTitle = findViewById(R.id.txtMovieTitle);
        txtMovieDirector = findViewById(R.id.txtMovieDirector);
        txtMovieDuration = findViewById(R.id.txtMovieDuration);
        txtMovieGenre = findViewById(R.id.txtMovieGenre);
        txtMovieDescription = findViewById(R.id.txtMovieDescription);
        btnContinue = findViewById(R.id.btnContinue);

        // Nhận dữ liệu từ Intent
        String title = getIntent().getStringExtra("MOVIE_TITLE");
        String director = getIntent().getStringExtra("MOVIE_DIRECTOR");
        String duration = getIntent().getStringExtra("MOVIE_DURATION");
        String genre = getIntent().getStringExtra("MOVIE_GENRE");
        String description = getIntent().getStringExtra("MOVIE_DESCRIPTION");
        int imageResId = getIntent().getIntExtra("MOVIE_IMAGE", R.drawable.movie_1);

        // Hiển thị dữ liệu lên giao diện
        txtMovieTitle.setText("Title: " + title);
        txtMovieDirector.setText(director);
        txtMovieDuration.setText("Duration: " + duration);
        txtMovieGenre.setText("Genre:                     " + genre);
        txtMovieDescription.setText(description);

        Glide.with(this)
                .load(imageResId)
                .placeholder(R.drawable.movie_1)
                .into(imgMovieBackground);

        btnContinue.setOnClickListener(v -> finish()); // Đóng Activity khi bấm nút Continue

    }
}
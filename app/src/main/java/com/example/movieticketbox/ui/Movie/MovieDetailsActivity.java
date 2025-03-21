package com.example.movieticketbox.ui.Movie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieticketbox.R;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Nhận dữ liệu từ Intent
        String title = getIntent().getStringExtra("MOVIE_TITLE");
        String rating = getIntent().getStringExtra("MOVIE_RATING");
        String duration = getIntent().getStringExtra("MOVIE_DURATION");
        String genre = getIntent().getStringExtra("MOVIE_GENRE");
        int imageResId = getIntent().getIntExtra("MOVIE_IMAGE", R.drawable.default_image);

        // Ánh xạ các TextView
        TextView txtTitle = findViewById(R.id.txtMovieTitle);
        TextView txtRating = findViewById(R.id.txtMovieRating);
        TextView txtDuration = findViewById(R.id.txtMovieDuration);
        TextView txtGenre = findViewById(R.id.txtMovieGenre);
        ImageView imgBackground = findViewById(R.id.imgMovieBackground);
        Button btnContinue = findViewById(R.id.btnContinue);
        ImageView btnBack = findViewById(R.id.btnBack);

        // Hiển thị thông tin phim
        txtTitle.setText(title != null ? title : "Unknown Title");
        txtRating.setText("Rating: " + (rating != null ? rating : "N/A"));
        txtDuration.setText("Duration: " + (duration != null ? duration : "N/A"));
        txtGenre.setText("Genre: " + (genre != null ? genre : "N/A"));
        imgBackground.setImageResource(imageResId);

        // Xử lý nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Xử lý nút Continue
        btnContinue.setOnClickListener(v ->
                Toast.makeText(MovieDetailsActivity.this, "Continue clicked!", Toast.LENGTH_SHORT).show()
        );
    }
}
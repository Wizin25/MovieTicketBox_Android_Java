package com.example.movieticketbox.ui.Movie;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieticketbox.R;

public class Movie extends Fragment {

    private MovieViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        TextView movieItem = view.findViewById(R.id.txtMovieTitle);
        movieItem.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
            intent.putExtra("MOVIE_TITLE", "Avengers: Infinity War");
            intent.putExtra("MOVIE_RATING", "4.8");
            intent.putExtra("MOVIE_DURATION", "2h29m");
            intent.putExtra("MOVIE_GENRE", "Action, Adventure, Sci-Fi");
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        CardView movie1 = getView().findViewById(R.id.cardMovie1);
        movie1.setOnClickListener(v -> openMovieDetails(
                "Shang-Chi: Legend of the Ten Rings",
                "4.0",
                "2 hours 5 minutes",
                "Action, Sci-fi",
                R.drawable.movie_1));

        CardView movie2 = getView().findViewById(R.id.cardMovie2);
        movie2.setOnClickListener(v -> openMovieDetails(
                "Batman v Superman: Dawn of Justice",
                "4.5",
                "2 hours 10 minutes",
                "Action, Sci-fi",
                R.drawable.movie_2));

        CardView movie3 = getView().findViewById(R.id.cardMovie3);
        movie3.setOnClickListener(v -> openMovieDetails(
                "Avengers: Infinity War",
                "4.8",
                "2 hours 29 minutes",
                "Action, Sci-fi",
                R.drawable.movie_3));

        CardView movie4 = getView().findViewById(R.id.cardMovie4);
        movie4.setOnClickListener(v -> openMovieDetails(
                "Guardians Of The Galaxy",
                "4.2",
                "2 hours 13 minutes",
                "Action, Sci-fi",
                R.drawable.movie_4));
    }


    // Hàm mở MovieDetailsActivity
    private void openMovieDetails(String title, String rating, String duration, String genre, int imageResId) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra("MOVIE_TITLE", title);
        intent.putExtra("MOVIE_RATING", rating);
        intent.putExtra("MOVIE_DURATION", duration);
        intent.putExtra("MOVIE_GENRE", genre);
        intent.putExtra("MOVIE_IMAGE", imageResId); // Thêm ID ảnh
        startActivity(intent);
    }

}
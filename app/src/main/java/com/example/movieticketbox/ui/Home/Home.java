package com.example.movieticketbox.ui.Home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieticketbox.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private HomeViewModel mViewModel;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvHi = view.findViewById(R.id.tvHi);
        TextView tvWelcomeBack = view.findViewById(R.id.tvWelcomeBack);
        ViewPager2 viewPager = view.findViewById(R.id.viewPagerMovies);

        tvHi.setText("hi, phan tấn phú 👋");
        tvWelcomeBack.setText("Welcome back");

        List<HomeMovie> movieList = new ArrayList<>();
        movieList.add(new HomeMovie(
                "Avengers: Infinity War",
                "4.8",
                "1.222",
                "2h29m",
                "Action, Adventure, Sci-fi",
                R.drawable.login_poster
        ));

        movieList.add(new HomeMovie(
                "Shang-Chi: Legend of the Ten Rings",
                "4.0",
                "1.225",
                "2h5m",
                "Action, Sci-fi",
                R.drawable.movie_1
        ));

        movieList.add(new HomeMovie(
                "Batman v Superman: Dawn of Justice",
                "4.5",
                "1.525",
                "2h10m",
                "Action, Sci-fi",
                R.drawable.movie_2
        ));


        // Có thể thêm các phim khác vào danh sách nếu muốn hiệu ứng trượt

        HomeMovieAdapter adapter = new HomeMovieAdapter(getContext(), movieList);
        viewPager.setAdapter(adapter);

// Hiệu ứng cuộn mượt + hiện poster 2 bên
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

// Gắn PageTransformer
        viewPager.setPageTransformer((page, position) -> {
            float scale = 0.85f + (1 - Math.abs(position)) * 0.15f;
            page.setScaleY(scale);
            page.setAlpha(0.7f + (1 - Math.abs(position)) * 0.3f);
            page.setTranslationX(-position * page.getWidth() * 0.1f); // Giảm 0.2f xuống 0.1f
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}
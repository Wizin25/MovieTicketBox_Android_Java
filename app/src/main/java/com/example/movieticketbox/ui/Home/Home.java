package com.example.movieticketbox.ui.Home;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieticketbox.R;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Home extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FrameLayout map;
    private HomeViewModel mViewModel;
    private RecyclerView recyclerViewComingSoon;
    private ComingSoonAdapter comingSoonAdapter;
    private List<ComingSoonMovie> comingSoonList;



    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        map = view.findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {
                LatLng cinema = new LatLng(10.7769, 106.7009); // ví dụ: TP.HCM
                googleMap.addMarker(new MarkerOptions().position(cinema).title("My Cinema"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cinema, 15f));
            });
        } else {
            Log.e("MapActivity", "Map fragment not found");
        }

        TextView tvHi = view.findViewById(R.id.tvHi);
        TextView tvWelcomeBack = view.findViewById(R.id.tvWelcomeBack);
        ViewPager2 viewPager = view.findViewById(R.id.viewPagerMovies);
        recyclerViewComingSoon = view.findViewById(R.id.recyclerViewComingSoon);
        recyclerViewComingSoon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        tvHi.setText("hi, phan tấn phú 👋");
        tvWelcomeBack.setText("Welcome back");
        //Now playing setup
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
        HomeMovieAdapter adapter = new HomeMovieAdapter(getContext(), movieList);
        viewPager.setAdapter(adapter);

        comingSoonList = new ArrayList<>();
        comingSoonList.add(new ComingSoonMovie("Avatar 2: The Way Of Water", "Adventure, Sci-fi", "20.12.2022", R.drawable.avatar_2));
        comingSoonList.add(new ComingSoonMovie("Ant Man Wasp: Quantumania", "Adventure, Sci-fi", "25.12.2022", R.drawable.antman));
        comingSoonList.add(new ComingSoonMovie("Fox puss in Boots: The last Wish", "Adventure, Animation", "27.12.2022", R.drawable.fox));
        comingSoonAdapter = new ComingSoonAdapter(getContext(), comingSoonList);
        recyclerViewComingSoon.setAdapter(comingSoonAdapter);

        // Hiệu ứng cuộn mượt + hiện poster 2 bên
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(1);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        // Gắn PageTransformer
        viewPager.setPageTransformer((page, position) -> {
            float scale = 0.75f + (1 - Math.abs(position)) * 0.25f;
            page.setScaleY(scale);
            page.setAlpha(0.5f + (1 - Math.abs(position)) * 0.5f);
            page.setTranslationX(-position * page.getWidth() * 0.01f); // Giảm 0.2f xuống 0.1f
        });

//        if (savedInstanceState == null) {
//            SupportMapFragment mapFragment = new SupportMapFragment();
//            getChildFragmentManager().beginTransaction()
//                    .add(R.id.map, mapFragment)
//                    .commit();
//            mapFragment.getMapAsync(this);
//        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(10.7769, 106.7009); // HCM
        mMap.addMarker(new MarkerOptions().position(location).title("My Cinema"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

    }

}
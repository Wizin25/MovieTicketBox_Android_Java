package com.example.movieticketbox.ui.Movie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieticketbox.MovieApi;
import com.example.movieticketbox.MovieApiService;
import com.example.movieticketbox.R;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Constructor mặc định (BẮT BUỘC CÓ)
    }

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        Log.d("RecyclerView_Debug", "onCreateView được gọi");

        recyclerView = view.findViewById(R.id.recyclerViewMovies);
        if (recyclerView == null) {
            Log.e("RecyclerView_Debug", "RecyclerView NULL");
        } else {
            Log.d("RecyclerView_Debug", "RecyclerView đã khởi tạo thành công");
        }
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        movieAdapter = new MovieAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(movieAdapter);

        fetchMovies();

        return view;
    }

    private void fetchMovies() {
        MovieApi movieAPI = MovieApiService.getApi();
        Call<MovieResponse> call = movieAPI.getMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API_RESPONSE", "JSON nhận được: " + new Gson().toJson(response.body()));

                    if (movieList == null) {
                        movieList = new ArrayList<>();
                    }

                    movieList.clear();
                    List<Movie> movies = response.body().getData();

                    if (movies != null && !movies.isEmpty()) {
                        movieList.addAll(movies);
                        movieAdapter.setMovies(movieList); // 🟢 Cập nhật Adapter
                        Log.d("API_SUCCESS", "Số lượng phim nhận được: " + movies.size());
                    } else {
                        Log.e("API_ERROR", "Danh sách phim rỗng hoặc null!");
                    }
                } else {
                    try {
                        Log.e("API_ERROR", "Lỗi phản hồi API: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi khi gọi API: " + t.getMessage());
            }
        });
    }
}

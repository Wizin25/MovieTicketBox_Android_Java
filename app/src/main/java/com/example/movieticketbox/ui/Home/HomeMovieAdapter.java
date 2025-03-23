package com.example.movieticketbox.ui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbox.R;

import java.util.List;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder> {
    private final Context context;
    private final List<HomeMovie> homeMovieList;

    public HomeMovieAdapter(Context context, List<HomeMovie> homeMovieList) {
        this.context = context;
        this.homeMovieList = homeMovieList;
    }

    @NonNull
    @Override
    public HomeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new HomeMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMovieViewHolder holder, int position) {
        HomeMovie movie = homeMovieList.get(position);

        holder.imgPoster.setImageResource(movie.getPosterResId());
        holder.tvTitle.setText(movie.getTitle());
        holder.tvDurationGenre.setText(movie.getDuration() + " - " + movie.getGenre());
        holder.tvRating.setText("⭐ " + movie.getRating() + " (" + movie.getRatingCount() + ")");
    }

    @Override
    public int getItemCount() {
        return homeMovieList.size();
    }

    public static class HomeMovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvDurationGenre, tvRating;

        public HomeMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDurationGenre = itemView.findViewById(R.id.tvDurationGenre);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}


package com.example.movieticketbox.ui.Movie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.movieticketbox.R;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Log.d("RecyclerView_Debug", "Đang bind movie: " + movie.getName());

        holder.txtMovieTitle.setText(movie.getName());
        holder.txtMovieGenre.setText(movie.getCategoryName());
        holder.txtMovieDuration.setText(movie.getDateStart() + " - " + movie.getDateEnd());

        Glide.with(holder.itemView.getContext())
                .load("https://your_image_base_url/" + movie.getImage())
                .placeholder(R.drawable.movie_1)
                .error(R.drawable.movie_1)
                .into(holder.imgMoviePoster);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("MOVIE_TITLE", movie.getName());
            intent.putExtra("MOVIE_DIRECTOR", movie.getDirectorName());
            intent.putExtra("MOVIE_DURATION", movie.getDateStart() + " - " + movie.getDateEnd());
            intent.putExtra("MOVIE_GENRE", movie.getCategoryName());
            intent.putExtra("MOVIE_IMAGE", R.drawable.movie_1);
            intent.putExtra("MOVIE_DESCRIPTION", movie.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMoviePoster;
        TextView txtMovieTitle, txtMovieGenre, txtMovieDuration;

        MovieViewHolder(View view) {
            super(view);
            imgMoviePoster = view.findViewById(R.id.imgMoviePoster);
            txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
            txtMovieGenre = view.findViewById(R.id.txtMovieGenre);
            txtMovieDuration = view.findViewById(R.id.txtMovieDuration);
        }
    }

    public void setMovies(List<Movie> movies) {
        if (movies == null) {
            Log.e("RecyclerView_Debug", "Danh sách phim NULL");
            return;
        }
        this.movies.clear();
        this.movies.addAll(movies);
        Log.d("RecyclerView_Debug", "Cập nhật danh sách phim, số lượng: " + movies.size());
        notifyDataSetChanged();
    }
}
package com.example.movieticketbox.ui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieticketbox.R;
import java.util.List;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ViewHolder> {

    private final Context context;
    private final List<ComingSoonMovie> comingSoonList;

    public ComingSoonAdapter(Context context, List<ComingSoonMovie> comingSoonList) {
        this.context = context;
        this.comingSoonList = comingSoonList;
    }

    @NonNull
    @Override
    public ComingSoonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coming_soon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonAdapter.ViewHolder holder, int position) {
        ComingSoonMovie movie = comingSoonList.get(position);
        holder.imgPoster.setImageResource(movie.getPosterResId());
        holder.tvTitle.setText(movie.getTitle());
        holder.tvGenre.setText(movie.getGenre());
        holder.tvDate.setText(movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return comingSoonList != null ? comingSoonList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView tvTitle, tvGenre, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgComingPoster);
            tvTitle = itemView.findViewById(R.id.tvComingTitle);
            tvGenre = itemView.findViewById(R.id.tvComingGenre);
            tvDate = itemView.findViewById(R.id.tvComingDate);
        }
    }
}

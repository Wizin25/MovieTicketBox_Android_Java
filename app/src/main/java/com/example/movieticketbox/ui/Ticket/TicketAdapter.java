package com.example.movieticketbox.ui.Ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbox.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private Context context;
    private List<Ticket> ticketList;

    public TicketAdapter(Context context, List<Ticket> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.movieName.setText("Movie: " + ticket.getMovieName());
        holder.showDateTime.setText("Date/Time: " + ticket.getShowDateTime());
        holder.seatName.setText("Seat: " +ticket.getSeatName());
        holder.price.setText(String.valueOf("Price: " + ticket.getPrice()));
        holder.status.setText("Status: " + (ticket.getStatus() == 1 ? "Booked" : "Available"));
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView movieName, showDateTime, seatName, price, status;

        public TicketViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.txtMovieName);
            showDateTime = itemView.findViewById(R.id.txtShowDateTime);
            seatName = itemView.findViewById(R.id.txtSeatName);
            price = itemView.findViewById(R.id.txtPrice);
            status = itemView.findViewById(R.id.txtStatus);
        }
    }
}

package com.example.movieticketbox.ui.Ticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbox.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<Ticket> tickets;

    // Constructor
    public TicketAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the ticket item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.bind(ticket); // Bind ticket data to view holder
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    // Method to update the ticket list
    public void setTickets(List<TicketFragment> tickets) {
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    // ViewHolder class to hold the views for each ticket
    public class TicketViewHolder extends RecyclerView.ViewHolder {
        // Declare the views
        private TextView tvMovieName;
        private TextView tvSeatInfo;
        private TextView tvShowDateTime;
        private TextView tvPrice;

        public TicketViewHolder(View itemView) {
            super(itemView);
            // Initialize the views
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvSeatInfo = itemView.findViewById(R.id.tvSeatInfo);
            tvShowDateTime = itemView.findViewById(R.id.tvShowDateTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

        // Bind ticket data to views
        public void bind(Ticket ticket) {
            tvMovieName.setText(ticket.getMovieName());
            tvSeatInfo.setText("Seat: " + ticket.getSeatName());
            tvShowDateTime.setText(ticket.getShowDateTime());
            tvPrice.setText("Price: " + ticket.getPrice() + " VND");
        }
    }
}

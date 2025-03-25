package com.example.movieticketbox.ui.Ticket;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieticketbox.R;
import java.util.ArrayList;
import java.util.List;

public class BookedTicketsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private List<Ticket> ticketList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_tickets, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewBookedTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a sample list of tickets
        ticketList = new ArrayList<>();
        ticketList.add(new Ticket(1, 1, "A1", "Avengers: Infinity War", "2025-07-15 10:00 AM", 120000, 1));
        ticketList.add(new Ticket(2, 2, "B2", "Shang-Chi: Legend of the Ten Rings", "2025-07-16 12:00 PM", 100000, 0));

        ticketAdapter = new TicketAdapter(getContext(), ticketList);
        recyclerView.setAdapter(ticketAdapter);

        return view;
    }
}

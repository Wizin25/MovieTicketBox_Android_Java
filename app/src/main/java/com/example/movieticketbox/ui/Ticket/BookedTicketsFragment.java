package com.example.movieticketbox.ui.Ticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbox.R;

import java.util.ArrayList;

public class BookedTicketsFragment extends Fragment {
    private TicketViewModel ticketViewModel;
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_booked_tickets, container, false);

        // Khởi tạo RecyclerView và Adapter
        //recyclerView = view.findViewById(R.id.recyclerView);
        ticketAdapter = new TicketAdapter(new ArrayList<>());
        recyclerView.setAdapter(ticketAdapter);

        // Quan sát LiveData từ ViewModel
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        ticketViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            if (tickets != null) {
                ticketAdapter.setTickets(tickets);  // Cập nhật Adapter với danh sách vé
            }
        });

        return view;
    }
}

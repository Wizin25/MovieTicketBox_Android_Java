package com.example.movieticketbox.ui.Ticket;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieticketbox.R;

public class TicketFragment extends Fragment {

    private TicketViewModel mViewModel;

    private ImageView btnBack, imgMoviePoster;
    private TextView txtMovieTitle, txtMovieDetails, txtShowTime, txtSeat, txtPrice, txtCinema, txtOrderID;

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_tickets, container, false);

        // Ánh xạ các thành phần UI
        btnBack = view.findViewById(R.id.btnBack);
        imgMoviePoster = view.findViewById(R.id.imgMoviePoster);
        txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
        txtMovieDetails = view.findViewById(R.id.txtMovieDetails);
        txtShowTime = view.findViewById(R.id.txtShowTime);
        txtSeat = view.findViewById(R.id.txtSeat);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtCinema = view.findViewById(R.id.txtCinema);
        txtOrderID = view.findViewById(R.id.txtOrderID);

        // Xử lý sự kiện nút back
        btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        // TODO: Thêm logic xử lý với ViewModel nếu cần
    }
}

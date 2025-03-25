package com.example.movieticketbox.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import com.example.movieticketbox.R;

public class Profile extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Gán sự kiện click đúng cách
        view.findViewById(R.id.btnMyTicket).setOnClickListener(v -> openMyTickets());
        view.findViewById(R.id.btnPaymentHistory).setOnClickListener(v -> openPaymentHistory());
        view.findViewById(R.id.btnChangePassword).setOnClickListener(v -> openChangePassword());
        view.findViewById(R.id.btnSelectSeat).setOnClickListener(v -> openSelectSeat());

        return view;

    }

    private void openSelectSeat() {
        startActivity(new Intent(getActivity(), MovieSelectionActivity.class));
    }

    private void openMyTickets() {
        startActivity(new Intent(getActivity(), MyTicketActivity.class));



    }

    private void openPaymentHistory() {
        startActivity(new Intent(getActivity(), PaymentHistoryActivity.class));
    }

    private void openChangePassword() {
        startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
    }
}
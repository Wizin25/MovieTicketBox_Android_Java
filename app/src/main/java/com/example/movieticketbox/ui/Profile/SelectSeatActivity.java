package com.example.movieticketbox.ui.Profile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieticketbox.R;
import java.util.ArrayList;
import java.util.List;

public class SelectSeatActivity extends AppCompatActivity {
    private GridLayout gridSeats;
    private TextView tvTotalPrice;
    private Button btnConfirmSeat;
    private List<Button> selectedSeats = new ArrayList<>();
    private int seatPrice = 70000; // Giá vé mỗi ghế
    private GridLayout gridSelectDate, gridSelectTime;
    private Button selectedDateButton, selectedTimeButton; // Lưu trạng thái button được chọn

    private String[] dateOptions = {"14/03", "15/03", "16/03", "17/03"};
    private String[] timeOptions = {"08:30", "10:45", "13:30", "15:30"};
    private String[] seatLabels = {
            "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
            "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
            "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8",
            "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8",
            "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        gridSeats = findViewById(R.id.gridSeats);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnConfirmSeat = findViewById(R.id.btnConfirmSeat);
        gridSelectDate = findViewById(R.id.gridSelectDate);
        gridSelectTime = findViewById(R.id.gridSelectTime);

        createSelectionButtons(gridSelectDate, dateOptions, true);
        createSelectionButtons(gridSelectTime, timeOptions, false);

        createSeatButtons();
    }

    private void toggleSeatSelection(Button seatButton) {
        if (selectedSeats.contains(seatButton)) {
            seatButton.setBackgroundColor(Color.parseColor("#555555"));
            selectedSeats.remove(seatButton);
        } else {
            seatButton.setBackgroundColor(Color.parseColor("#FFA500"));
            selectedSeats.add(seatButton);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int total = selectedSeats.size() * seatPrice;
        tvTotalPrice.setText("Total: " + total + " VND");
    }

    private void createSeatButtons() {
        for (String seatLabel : seatLabels) {
            Button seatButton = new Button(this);
            seatButton.setText(seatLabel);
            seatButton.setTextSize(10); // Giảm kích thước chữ
            seatButton.setPadding(4, 4, 4, 4);
            seatButton.setBackgroundResource(R.drawable.seat_border); // Thêm viền quanh ghế
            seatButton.setTextColor(Color.WHITE);

            // Giảm kích thước ghế để vừa đủ 8 ghế mỗi hàng
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0; // Cho phép tự động chia đều
            params.height = 80; // Giảm chiều cao
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(4, 4, 4, 4);
            seatButton.setLayoutParams(params);

            seatButton.setOnClickListener(v -> toggleSeatSelection(seatButton));
            gridSeats.addView(seatButton);
        }
    }

    private void createSelectionButtons(GridLayout gridLayout, String[] options, boolean isDate) {
        for (String option : options) {
            Button button = new Button(this);
            button.setText(option);
            button.setTextSize(14);
            button.setTextColor(Color.WHITE);
            button.setBackgroundResource(R.drawable.selection_button); // Thêm viền
            button.setPadding(12, 12, 12, 12);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 100;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(8, 8, 8, 8);
            button.setLayoutParams(params);

            button.setOnClickListener(v -> {
                if (isDate) {
                    if (selectedDateButton != null) {
                        selectedDateButton.setBackgroundResource(R.drawable.selection_button);
                    }
                    selectedDateButton = button;
                } else {
                    if (selectedTimeButton != null) {
                        selectedTimeButton.setBackgroundResource(R.drawable.selection_button);
                    }
                    selectedTimeButton = button;
                }
                button.setBackgroundResource(R.drawable.selection_button_selected);
            });

            gridLayout.addView(button);
        }
    }


}
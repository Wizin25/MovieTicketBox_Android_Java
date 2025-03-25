package com.example.movieticketbox.ui.Profile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectSeatActivity extends AppCompatActivity {
    private GridLayout gridSeats;
    private TextView tvTotalPrice;
    private Button btnConfirmSeat;
    private List<Button> selectedSeats = new ArrayList<>();
    private int seatPrice = 70000;
    private GridLayout gridSelectDate, gridSelectTime;
    private Button selectedDateButton, selectedTimeButton;

    // Dữ liệu cũ (giữ lại để không ảnh hưởng)
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

    // Biến mới thêm
    private int movieId = -1;
    private int selectedShowtimeId = -1;
    private List<JSONObject> showtimeDataList = new ArrayList<>();

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

        // ✅ Nhận movieId từ intent
        // ✅ Nhận movieId từ intent
        movieId = getIntent().getIntExtra("movieId", -1);
        selectedShowtimeId = getIntent().getIntExtra("showtimeId", -1);

// ✅ Thêm log kiểm tra
        Log.d("DEBUG", "movieId = " + movieId);
        Log.d("DEBUG", "selectedShowtimeId = " + selectedShowtimeId);


// ✅ Nếu đã có movieId + showtimeId thì gọi API lấy ghế luôn
        if (movieId != -1 && selectedShowtimeId != -1) {
            fetchSeatsFromApi(selectedShowtimeId);
        }



        // ✅ Gọi API lấy showtimes
        fetchShowtimes(movieId);
    }

    private void fetchShowtimes(int movieId) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://46e3-42-112-78-218.ngrok-free.app/api/showtimes/movieid/" + movieId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "text/plain")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) return;

                String responseBody = response.body().string();
                try {
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray data = json.getJSONArray("data");

                    runOnUiThread(() -> {
                        List<String> dateList = new ArrayList<>();
                        List<String> timeList = new ArrayList<>();

                        for (int i = 0; i < data.length(); i++) {
                            try {
                                JSONObject showtime = data.getJSONObject(i);
                                showtimeDataList.add(showtime);

                                String showDateTime = showtime.getString("showDateTime");
                                String[] parts = showDateTime.split("T");
                                if (parts.length == 2) {
                                    String date = parts[0];
                                    String time = parts[1].substring(0, 5);

                                    if (!dateList.contains(date)) dateList.add(date);
                                    if (!timeList.contains(time)) timeList.add(time);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        createSelectionButtons(gridSelectDate, dateList.toArray(new String[0]), true);
                        createSelectionButtons(gridSelectTime, timeList.toArray(new String[0]), false);
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fetchSeatsFromApi(int showtimeId) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://46e3-42-112-78-218.ngrok-free.app/api/seats/showtimeid/" + showtimeId;


        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "text/plain")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) return;

                String body = response.body().string();
                Log.d("DEBUG", "Seats API Response: " + body); // Log phản hồi từ API
                try {
                    JSONObject json = new JSONObject(body);
                    JSONArray data = json.getJSONArray("data");

                    runOnUiThread(() -> {
                        gridSeats.removeAllViews();

                        List<Button> remainingSelectedSeats = new ArrayList<>();

                        for (int i = 0; i < data.length(); i++) {
                            try {
                                JSONObject seat = data.getJSONObject(i);
                                String seatNumber = seat.getString("seatNumber");
                                boolean isAvailable = seat.getBoolean("status");

                                Button seatButton = createSeatButtonReturn(seatNumber, isAvailable);

                                // Nếu seat được chọn từ trước, gán lại trạng thái Selected
                                for (Button selected : selectedSeats) {
                                    if (selected.getText().toString().equals(seatNumber)) {
                                        seatButton.setBackgroundColor(Color.parseColor("#FFA500"));
                                        remainingSelectedSeats.add(seatButton);
                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        selectedSeats = remainingSelectedSeats; // cập nhật lại danh sách đã chọn
                        updateTotalPrice();
                        Log.d("DEBUG", "Seats added: " + gridSeats.getChildCount());
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Button createSeatButtonReturn(String seatLabel, boolean isAvailable) {
        Button seatButton = new Button(this);
        seatButton.setText(seatLabel);
        seatButton.setTextSize(10);
        seatButton.setPadding(4, 4, 4, 4);
        seatButton.setTextColor(Color.WHITE);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 80;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(4, 4, 4, 4);
        seatButton.setLayoutParams(params);

        if (isAvailable) {
            seatButton.setBackgroundResource(R.drawable.seat_border);
            seatButton.setOnClickListener(v -> toggleSeatSelection(seatButton));
        } else {
            seatButton.setBackgroundColor(Color.parseColor("#8B4513"));
            seatButton.setEnabled(false);
        }

        gridSeats.addView(seatButton);
        return seatButton;
    }


    private void createSeatButton(String seatLabel, boolean isAvailable) {
        Button seatButton = new Button(this);
        seatButton.setText(seatLabel);
        seatButton.setTextSize(10);
        seatButton.setPadding(4, 4, 4, 4);
        seatButton.setTextColor(Color.WHITE);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 80;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(4, 4, 4, 4);
        seatButton.setLayoutParams(params);

        if (isAvailable) {
            seatButton.setBackgroundResource(R.drawable.seat_border);
            seatButton.setOnClickListener(v -> toggleSeatSelection(seatButton));
        } else {
            seatButton.setBackgroundColor(Color.parseColor("#8B4513")); // màu ghế đã đặt
            seatButton.setEnabled(false); // không cho chọn
        }

        gridSeats.addView(seatButton);
    }


    private void findShowtimeIdAndFetchSeats(String selectedDate, String selectedTime) {
        for (JSONObject obj : showtimeDataList) {
            try {
                String dateTime = obj.getString("showDateTime");
                String[] parts = dateTime.split("T");
                if (parts.length == 2) {
                    String date = parts[0];
                    String time = parts[1].substring(0, 5);

                    if (date.equals(selectedDate) && time.equals(selectedTime)) {
                        selectedShowtimeId = obj.getInt("id");
                        fetchSeatsFromApi(selectedShowtimeId);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void toggleSeatSelection(Button seatButton) {
        Log.d("DEBUG", "Seat selected: " + seatButton.getText().toString()); // Log thông tin ghế đã chọn
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

    private void createSelectionButtons(GridLayout gridLayout, String[] options, boolean isDate) {
        for (String option : options) {
            Button button = new Button(this);
            button.setText(option);
            button.setTextSize(14);
            button.setTextColor(Color.WHITE);
            button.setBackgroundResource(R.drawable.selection_button);
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

                    String selectedDate = selectedDateButton != null ? selectedDateButton.getText().toString() : "";
                    String selectedTime = button.getText().toString();
                    findShowtimeIdAndFetchSeats(selectedDate, selectedTime);
                }
                button.setBackgroundResource(R.drawable.selection_button_selected);
            });

            gridLayout.addView(button);
        }
    }
}
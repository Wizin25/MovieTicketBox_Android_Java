package com.example.movieticketbox.ui.Ticket;


import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.movieticketbox.ApiService;
import com.example.movieticketbox.ui.ApiClient;
import java.util.List;

public class TicketViewModel extends ViewModel {
    private MutableLiveData<List<TicketFragment>> ticketsLiveData = new MutableLiveData<>();

    public LiveData<List<TicketFragment>> getTickets() {
        loadTickets();
        return ticketsLiveData;
    }

    private void loadTickets() {
        // Tạo Retrofit API service
        ApiService apiService = ApiClient.getApiService();

        // Gọi API
        apiService.getTickets().enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ticketsLiveData.postValue(response.body().getData());
                } else {
                    Log.e("TicketViewModel", "Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Log.e("TicketViewModel", "API Error: " + t.getMessage());
            }
        });
    }
}
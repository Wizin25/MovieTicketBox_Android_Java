package com.example.movieticketbox;

import com.example.movieticketbox.Login.LoginRequest;
import com.example.movieticketbox.Login.LoginResponse;
import com.example.movieticketbox.Login.RegisterRequest;
import com.example.movieticketbox.Login.RegisterResponse;
import com.example.movieticketbox.ui.Ticket.TicketResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Authentication/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("Authentication/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET("api/tickets")  // Endpoint mà bạn sẽ gọi
    Call<TicketResponse> getTickets();
}

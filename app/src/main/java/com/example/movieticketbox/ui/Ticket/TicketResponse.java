package com.example.movieticketbox.ui.Ticket;


import java.util.List;

public class TicketResponse {
    private boolean status;
    private List<TicketFragment> data;
    private String error;
    private int errorCode;
    private String message;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TicketFragment> getData() {
        return data;
    }

    public void setData(List<TicketFragment> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
package com.example.movieticketbox.ui.Movie;


import java.util.List;

public class MovieResponse {
    private boolean status;
    private List<Movie> data;
    private String error;
    private int errorCode;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public List<Movie> getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}

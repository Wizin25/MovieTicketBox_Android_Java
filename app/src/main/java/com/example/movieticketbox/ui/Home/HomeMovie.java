package com.example.movieticketbox.ui.Home;

public class HomeMovie {
    private final String title;
    private final String rating;
    private final String ratingCount;
    private final String duration;
    private final String genre;
    private final int posterResId;

    public HomeMovie(String title, String rating, String ratingCount, String duration, String genre, int posterResId) {
        this.title = title;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.duration = duration;
        this.genre = genre;
        this.posterResId = posterResId;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getPosterResId() {
        return posterResId;
    }
}

package com.example.movieticketbox.ui.Home;


public class ComingSoonMovie {
    private final String title;
    private final String genre;
    private final String releaseDate;
    private final int posterResId;

    public ComingSoonMovie(String title, String genre, String releaseDate, int posterResId) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterResId = posterResId;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getReleaseDate() { return releaseDate; }
    public int getPosterResId() { return posterResId; }
}
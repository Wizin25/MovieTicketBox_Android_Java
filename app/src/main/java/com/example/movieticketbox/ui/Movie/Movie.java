package com.example.movieticketbox.ui.Movie;


public class Movie {
    private int id;
    private String name;
    private String dateStart;
    private String dateEnd;
    private String image;
    private int status;
    private String directorName;
    private String description;
    private int categoryId;
    private String categoryName;

    public Movie(int id, String name, String dateStart, String dateEnd, String image, int status,
                 String directorName, String description, int categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.image = image;
        this.status = status;
        this.directorName = directorName;
        this.description = description;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getImage() {
        return image;
    }

    public int getStatus() {
        return status;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}


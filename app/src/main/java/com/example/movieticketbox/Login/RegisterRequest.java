package com.example.movieticketbox.Login;

public class RegisterRequest {
    private String fullname;
    private String email;
    private String username;
    private String password;
    private String rePassword;

    // Constructor
    public RegisterRequest(String fullname,String email, String username, String password, String rePassword) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
    }

    // Getters and Setters
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}


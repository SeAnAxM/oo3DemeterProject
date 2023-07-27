package com.example.oo3demeterproject;

public class UserSession {

    private static UserSession instance = new UserSession();

    private String username;
    private String appearance;
    private User currentUser;

    private UserSession() {
    }

    public static UserSession getInstance() {
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
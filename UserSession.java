/*
 * Classname: UserSession.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class that represents the current user's session. It stores the user data,
 * such as the username and appearance, and the user object itself. This class provides global access to the user's
 * data across different parts of the application. It provides getter and setter methods for the session data. The
 * getInstance() method ensures that only one instance of the UserSession class is instantiated, providing a global
 * point of access to the instance.
 */

package com.example.oo3demeterproject;

/**
 * This class represents the current user session.
 * Stores user data such as username and appearance.
 */
public class UserSession {

    private static UserSession instance = new UserSession();

    private String username;
    private String appearance;
    private User currentUser;

    private UserSession() {
    }

    /**
     * Retrieves the instance of the UserSession.
     *
     * @return The instance of UserSession.
     */
    public static UserSession getInstance() {
        return instance;
    }

    /**
     * Sets the username of the current user session.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the current user session.
     *
     * @return The username of the current user session.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the appearance of the current user session.
     *
     * @param appearance The appearance to set.
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    /**
     * Retrieves the appearance of the current user session.
     *
     * @return The appearance of the current user session.
     */
    public String getAppearance() {
        return appearance;
    }

    /**
     * Sets the current user of the session.
     *
     * @param user The user to set as the current user.
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Retrieves the current user of the session.
     *
     * @return The current user of the session.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
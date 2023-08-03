/*
 * Classname: User.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The User class represents a user in the application. It contains information about the user's
 * performance in an escape room game, such as the best times they have achieved in each room and overall,
 * and the achievements they have earned. This class provides methods to update and access this information.
 * Achievements are calculated based on the user's best times.
 */


package com.example.oo3demeterproject;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * User class for the application.
 * This class holds user data such as escape room times and achievements.
 */
public class User {

    private boolean hasClearedEscapeRoom;
    private boolean hasClearedEscapeRoomIn3Minutes;
    private boolean hasClearedEscapeRoomIn2Minutes;
    private boolean hasClearedEscapeRoomIn1Minute;

    private long bestTime = Long.MAX_VALUE;
    private long bestTimeRoom1 = Long.MAX_VALUE;
    private long bestTimeRoom2 = Long.MAX_VALUE;
    private long bestTimeRoom3 = Long.MAX_VALUE;
    private List<Long> timesRoom1 = new ArrayList<>();
    private List<Long> timesRoom2 = new ArrayList<>();
    private List<Long> timesRoom3 = new ArrayList<>();

    /**
     * Update achievements based on the best time of the user.
     */
    public void updateAchievements() {
        hasClearedEscapeRoom = bestTime != Long.MAX_VALUE;
        hasClearedEscapeRoomIn3Minutes = bestTime <= 180; // less than or equal to 3 minutes
        hasClearedEscapeRoomIn2Minutes = bestTime <= 120; // less than or equal to 2 minutes
        hasClearedEscapeRoomIn1Minute = bestTime <= 60; // less than or equal to 1 minute
    }

    /**
     * Check if the user has cleared the escape room.
     * @return True if the user has cleared the escape room, otherwise false.
     */
    public boolean hasClearedEscapeRoom() {
        return hasClearedEscapeRoom;
    }

    /**
     * Check if the user has cleared the escape room within 3 minutes.
     * @return True if the user has cleared the escape room within 3 minutes, otherwise false.
     */
    public boolean hasClearedEscapeRoomIn3Minutes() {
        return hasClearedEscapeRoomIn3Minutes;
    }

    /**
     * Check if the user has cleared the escape room within 2 minutes.
     * @return True if the user has cleared the escape room within 2 minutes, otherwise false.
     */
    public boolean hasClearedEscapeRoomIn2Minutes() {
        return hasClearedEscapeRoomIn2Minutes;
    }

    /**
     * Check if the user has cleared the escape room within 1 minute.
     * @return True if the user has cleared the escape room within 1 minute, otherwise false.
     */
    public boolean hasClearedEscapeRoomIn1Minute() {
        return hasClearedEscapeRoomIn1Minute;
    }

    /**
     * Set the best time of the user.
     * @param bestTime The best time of the user.
     */
    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
    }

    /**
     * Set the best time of the user in room 1.
     * @param bestTimeRoom1 The best time of the user in room 1.
     */
    public void setBestTimeRoom1(long bestTimeRoom1) {
        this.bestTimeRoom1 = bestTimeRoom1;
    }

    /**
     * Set the best time of the user in room 2.
     * @param bestTimeRoom2 The best time of the user in room 2.
     */
    public void setBestTimeRoom2(long bestTimeRoom2) {
        this.bestTimeRoom2 = bestTimeRoom2;
    }

    /**
     * Set the best time of the user in room 3.
     * @param bestTimeRoom3 The best time of the user in room 3.
     */
    public void setBestTimeRoom3(long bestTimeRoom3) {
        this.bestTimeRoom3 = bestTimeRoom3;
    }

    /**
     * Get the best time of the user.
     * @return The best time of the user.
     */
    public long getBestTime() {
        return bestTime;
    }

    /**
     * Add a time to the list of times for room 1.
     * @param timeRoom1 The time to be added.
     */
    public void addTimeRoom1(long timeRoom1) {
        this.timesRoom1.add(timeRoom1);
    }

    /**
     * Add a time to the list of times for room 2.
     * @param timeRoom2 The time to be added.
     */
    public void addTimeRoom2(long timeRoom2) {
        this.timesRoom2.add(timeRoom2);
    }

    /**
     * Add a time to the list of times for room 3.
     * @param timeRoom3 The time to be added.
     */
    public void addTimeRoom3(long timeRoom3) {
        this.timesRoom3.add(timeRoom3);
    }

    /**
     * Remove the most recent time from the list of times for room 1.
     */
    public void removeRecentTimeRoom1() {
        if (!timesRoom1.isEmpty()) {
            timesRoom1.remove(timesRoom1.size() - 1);
        }
    }

    /**
     * Remove the most recent time from the list of times for room 2.
     */
    public void removeRecentTimeRoom2() {
        if (!timesRoom2.isEmpty()) {
            timesRoom2.remove(timesRoom2.size() - 1);
        }
    }

    /**
     * Get the best time from the list of times for room 1.
     * @return The best time for room 1, or Long.MAX_VALUE if there are no times recorded yet.
     */
    public long getBestTimeRoom1() {
        if (timesRoom1.isEmpty()) {
            return Long.MAX_VALUE;
        }
        return Collections.min(timesRoom1);
    }

    /**
     * Get the best time from the list of times for room 2.
     * @return The best time for room 2, or Long.MAX_VALUE if there are no times recorded yet.
     */
    public long getBestTimeRoom2() {
        if (timesRoom2.isEmpty()) {
            return Long.MAX_VALUE;
        }
        return Collections.min(timesRoom2);
    }

    /**
     * Get the best time from the list of times for room 3.
     * @return The best time for room 3, or Long.MAX_VALUE if there are no times recorded yet.
     */
    public long getBestTimeRoom3() {
        if (timesRoom3.isEmpty()) {
            return Long.MAX_VALUE;
        }
        return Collections.min(timesRoom3);
    }
}

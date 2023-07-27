package com.example.oo3demeterproject;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

    public void setClearedEscapeRoom(boolean hasClearedEscapeRoom) {
        this.hasClearedEscapeRoom = hasClearedEscapeRoom;
    }

    public void setClearedEscapeRoomIn3Minutes(boolean hasClearedEscapeRoomIn3Minutes) {
        this.hasClearedEscapeRoomIn3Minutes = hasClearedEscapeRoomIn3Minutes;
    }

    public void setClearedEscapeRoomIn2Minutes(boolean hasClearedEscapeRoomIn2Minutes) {
        this.hasClearedEscapeRoomIn2Minutes = hasClearedEscapeRoomIn2Minutes;
    }

    public void setClearedEscapeRoomIn1Minute(boolean hasClearedEscapeRoomIn1Minute) {
        this.hasClearedEscapeRoomIn1Minute = hasClearedEscapeRoomIn1Minute;
    }

    public void updateAchievements() {
        hasClearedEscapeRoom = bestTime != Long.MAX_VALUE;
        hasClearedEscapeRoomIn3Minutes = bestTime <= 180; // less than or equal to 3 minutes
        hasClearedEscapeRoomIn2Minutes = bestTime <= 120; // less than or equal to 2 minutes
        hasClearedEscapeRoomIn1Minute = bestTime <= 60; // less than or equal to 1 minute
    }

    public boolean hasClearedEscapeRoom() {
        return hasClearedEscapeRoom;
    }

    public boolean hasClearedEscapeRoomIn3Minutes() {
        return hasClearedEscapeRoomIn3Minutes;
    }

    public boolean hasClearedEscapeRoomIn2Minutes() {
        return hasClearedEscapeRoomIn2Minutes;
    }

    public boolean hasClearedEscapeRoomIn1Minute() {
        return hasClearedEscapeRoomIn1Minute;
    }

    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
    }

    public void setBestTimeRoom1(long bestTimeRoom1) {
        this.bestTimeRoom1 = bestTimeRoom1;
    }

    public void setBestTimeRoom2(long bestTimeRoom2) {
        this.bestTimeRoom2 = bestTimeRoom2;
    }

    public void setBestTimeRoom3(long bestTimeRoom3) {
        this.bestTimeRoom3 = bestTimeRoom3;
    }

    public long getBestTime() {
        return bestTime;
    }


    public void addTimeRoom1(long timeRoom1) {
        this.timesRoom1.add(timeRoom1);
    }

    public void addTimeRoom2(long timeRoom2) {
        this.timesRoom2.add(timeRoom2);
    }

    public void addTimeRoom3(long timeRoom3) {
        this.timesRoom3.add(timeRoom3);
    }

    public void removeRecentTimeRoom1() {
        if (!timesRoom1.isEmpty()) {
            timesRoom1.remove(timesRoom1.size() - 1);
        }
    }

    public void removeRecentTimeRoom2() {
        if (!timesRoom2.isEmpty()) {
            timesRoom2.remove(timesRoom2.size() - 1);
        }
    }

    public void removeRecentTimeRoom3() {
        if (!timesRoom3.isEmpty()) {
            timesRoom3.remove(timesRoom3.size() - 1);
        }
    }

    public long getBestTimeRoom1() {
        if (timesRoom1.isEmpty()) {
            return Long.MAX_VALUE;  // Or whatever you want to return when there's no time recorded yet
        }
        return Collections.min(timesRoom1);
    }

    public long getBestTimeRoom2() {
        if (timesRoom2.isEmpty()) {
            return Long.MAX_VALUE;  // Or whatever you want to return when there's no time recorded yet
        }
        return Collections.min(timesRoom2);
    }

    public long getBestTimeRoom3() {
        if (timesRoom3.isEmpty()) {
            return Long.MAX_VALUE;  // Or whatever you want to return when there's no time recorded yet
        }
        return Collections.min(timesRoom3);
    }


}

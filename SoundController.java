/*
 * Classname: SoundController.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class is a Singleton class that provides a centralized point for managing and controlling all
 * sound effects and background music throughout the application. It ensures that only one instance of the
 * SoundController class is instantiated and provides global access to it. It can play, stop, and control the sounds
 * and music needed at different stages of the game, such as the main menu and specific game layouts.
 */

package com.example.oo3demeterproject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * SoundController is a Singleton class that controls sound effects and background music throughout the application.
 * Implements the SoundPlayer interface.
 */
public class SoundController implements SoundPlayer{

    private static SoundController instance;
    private MediaPlayer mediaPlayer;
    private String currentSoundFile;

    private SoundController() {}

    /**
     * Retrieves the single instance of the SoundController, creating it if it does not exist.
     *
     * @return The single instance of SoundController.
     */
    public static SoundController getInstance() {
        if (instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    /**
     * Plays a sound file. If the same sound file is already playing, it continues without interruption.
     *
     * @param soundFilePath The file path of the sound to be played.
     */
    public void playSound(String soundFilePath) {
        if (currentSoundFile == null || !currentSoundFile.equals(soundFilePath)) {
            stopSound();
            Media sound = new Media(new File(soundFilePath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            currentSoundFile = soundFilePath;
        }
    }

    /**
     * Stops the currently playing sound if there is one.
     */
    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            currentSoundFile = null;
        }
    }

    /**
     * Plays the main menu background music.
     */
    public void playMainMenuMusic() {
        playSound("src/main/resources/assets/mainmenu.wav");
    }

    /**
     * Plays the background music for a specified game.
     *
     * @param gameNumber The number of the game for which to play music.
     * @throws IllegalArgumentException If the game number is invalid.
     */
    public void playGameMusic(int gameNumber) {
        switch (gameNumber) {
            case 1:
                playSound("src/main/resources/assets/game1.wav");
                break;
            case 2:
                playSound("src/main/resources/assets/game2.wav");
                break;
            case 3:
                playSound("src/main/resources/assets/game3.wav");
                break;
            default:
                throw new IllegalArgumentException("Invalid game number");
        }
    }

    /**
     * Plays the sound effect for a room being cleared.
     */
    public void playRoomClearSound() {
        playSound("src/main/resources/assets/roomclear.wav");
    }


}
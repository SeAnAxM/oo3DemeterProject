package com.example.oo3demeterproject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundController {

    private static SoundController instance;
    private MediaPlayer mediaPlayer;
    private String currentSoundFile;

    private SoundController() {}

    public static SoundController getInstance() {
        if (instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    public void playSound(String soundFilePath) {
        if (currentSoundFile == null || !currentSoundFile.equals(soundFilePath)) {
            stopSound();  // stop any currently playing sound
            Media sound = new Media(new File(soundFilePath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            currentSoundFile = soundFilePath;
        }
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            currentSoundFile = null;
        }
    }

    public void playMainMenuMusic() {
        playSound("src/main/resources/assets/mainmenu.wav");
    }

    public void playGame1Music() {
        playSound("src/main/resources/assets/game1.wav");
    }

    public void playGame2Music() {
        playSound("src/main/resources/assets/game2.wav");
    }

    public void playGame3Music() {
        playSound("src/main/resources/assets/game3.wav");
    }

    public void playRoomClearSound() {
        playSound("src/main/resources/assets/roomclear.wav");
    }


}
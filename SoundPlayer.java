/*
 * Classname: SoundPlayer.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The SoundPlayer interface defines the methods necessary for playing in-game sounds.
 * It includes methods to play and stop sounds, as well as methods to play specific types of music or sound effects,
 * such as main menu music, game-specific music, and the sound effect for clearing a room.
 */


package com.example.oo3demeterproject;

/**
 * Interface defining the methods required for an object that can play sound.
 */
public interface SoundPlayer {
    /**
     * Plays a sound from a specified file path.
     *
     * @param soundFilePath Path to the sound file.
     */
    void playSound(String soundFilePath);

    /**
     * Stops the currently playing sound.
     */
    void stopSound();

    /**
     * Plays the main menu music.
     */
    void playMainMenuMusic();

    /**
     * Plays the music for a specific game.
     *
     * @param gameNumber Number of the game for which to play music.
     */
    void playGameMusic(int gameNumber);

    /**
     * Plays the sound effect for clearing a room.
     */
    void playRoomClearSound();
}

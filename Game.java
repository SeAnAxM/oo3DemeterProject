/*
 * Classname: Game.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The Game interface outlines the necessary methods that any game object must implement. These methods
 * control the primary game loop, including starting the game, ending the game, and completing a level.
 */


package com.example.oo3demeterproject;

/**
 * Interface defining the methods required for a game object.
 */
public interface Game {
    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Ends the game.
     */
    void endGame();

    /**
     * Completes a level in the game.
     */
    void completeLevel();
}
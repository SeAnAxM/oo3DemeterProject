/*
 * Classname: LayoutController.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This abstract class acts as a blueprint for game layout controllers. The class provides core
 * functionalities for all game layout controllers. It includes functionalities such as starting the game, managing
 * the game timer, controlling the game music, and associating a GameController with the layout. The abstract methods
 * initialize() and getGameNumber() are designed to be overridden by subclasses to allow for layout-specific
 * customizations.
 */

package com.example.oo3demeterproject;

/**
 * Abstract class representing a game layout controller.
 * This class provides the basic structure and functionalities for all game layout controllers.
 * All layout controllers need to implement initialize() and getGameNumber() methods, and can use the provided methods to control game states and music.
 */
public abstract class LayoutController {
    protected long startTime;
    protected GameController gameController;

    /**
     * Sets the GameController for this layout.
     *
     * @param gameController the GameController to be set
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Starts the game.
     * Initializes the layout and starts the game timer.
     */
    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
        System.out.println("Timer started");
    }

    /**
     * Starts the game music.
     * Requests the GameController to start the game music specific to this layout.
     */
    public void startMusic() {
        gameController.getSoundController().playGameMusic(getGameNumber());
    }

    /**
     * Stops the game music.
     * Requests the GameController to stop the game music.
     */
    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }

    /**
     * Initializes the layout.
     * This method should be implemented by subclasses to provide specific layout initialization.
     */
    public abstract void initialize();

    /**
     * Returns the game number specific to this layout.
     * This method should be implemented by subclasses to return a unique game number.
     *
     * @return the game number
     */
    public abstract int getGameNumber();
}

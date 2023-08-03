/*
 * Classname: GameController.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The GameController class manages the game state and flow, including setup, transitions, and game-end
 * scenarios. It is responsible for implementing the Game interface. It sets up and controls different game scenes
 * or 'layouts', each with its own controller. It also manages user data such as username and appearance, tracks game
 * time, and communicates with the SoundController for sound effects and music. Furthermore, GameController manages
 * the progression across the levels of the game and keeps track of player achievements.
 */


package com.example.oo3demeterproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.Duration;
import java.time.Instant;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.Parent;
import javafx.application.Platform;
import java.io.IOException;
import javafx.scene.control.DialogPane;

/**
 * Implements the Game interface, representing a GameController that manages game state and flow.
 * The GameController controls game progress across different layouts and keeps track of user data and game time.
 */
public class GameController implements Game{
    private Stage stage;
    private SoundController soundController;
    private Instant startTime, totalStartTime;
    private Instant endTime;
    private User user;

    private String username;
    private String appearance;

    /**
     * Constructs a GameController with a given Stage.
     *
     * @param stage The stage to be used for the game
     */
    public GameController(Stage stage) {
        this.stage = stage;
        this.soundController = SoundController.getInstance();
        this.currentLevel = 0;
    }

    /**
     * Sets the User for this game.
     *
     * @param user The User to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the username associated with the current game instance.
     *
     * @return A String representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the current game instance.
     *
     * @param username A String representing the username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the appearance associated with the current game instance.
     *
     * @return A String representing the appearance.
     */
    public String getAppearance() {
        return appearance;
    }

    /**
     * Sets the appearance for the current game instance.
     *
     * @param appearance A String representing the appearance to be set.
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    private Scene layout1Scene;
    private Scene layout2Scene;
    private Scene layout3Scene;

    private Layout1Controller layout1Controller;
    private Layout2Controller layout2Controller;
    private Layout3Controller layout3Controller;
    private int currentLevel;

    /**
     * Begins the game by setting up the scenes and controllers for the different layouts.
     * Initializes the start time for the game.
     */
    @Override
    public void startGame() {
        try {
            this.setUser(UserSession.getInstance().getCurrentUser());

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Layout1.fxml"));
            layout1Scene = new Scene(loader1.load());
            layout1Controller = loader1.getController();
            layout1Controller.setGameController(this);
            layout1Controller.setUsername(this.getUsername());
            layout1Controller.setAppearance(this.getAppearance());

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Layout2.fxml"));
            layout2Scene = new Scene(loader2.load(), 800, 600);
            layout2Controller = loader2.getController();
            layout2Controller.setGameController(this);

            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Layout3.fxml"));
            layout3Scene = new Scene(loader3.load(), 800, 600);
            layout3Controller = loader3.getController();
            layout3Controller.setGameController(this);


            startLayout1();
            startTime = Instant.now();
            totalStartTime = Instant.now();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the first layout of the game.
     * The game scene is switched to layout1 and its game and music are started.
     */
    public void startLayout1() {
        stage.setScene(layout1Scene);
        layout1Controller.startGame();
        layout1Controller.startMusic();
        currentLevel = 1;
    }

    /**
     * Starts the second layout of the game.
     * The game scene is switched to layout2 and its game and music are started.
     */
    public void startLayout2() {
        currentLevel = 1;
        if (currentLevel < 1) {
            return;
        }

        stage.setScene(layout2Scene);
        layout2Controller.startGame();
        layout2Controller.startMusic();
        currentLevel = 2;
    }

    /**
     * Starts the third layout of the game.
     * The game scene is switched to layout3 and its game and music are started.
     */
    public void startLayout3() {
        currentLevel = 2;
        if (currentLevel < 2) {
            return;
        }

        stage.setScene(layout3Scene);
        layout3Controller.startGame();
        layout3Controller.startMusic();
        currentLevel = 3;
    }

    /**
     * Completes the current level of the game and switches to the next level.
     * The completion time for the level is recorded, and the next level is started if available.
     */
    @Override
    public void completeLevel() {
        User user = UserSession.getInstance().getCurrentUser();
        endTime = Instant.now();
        if (currentLevel == 1) {
            layout1Controller.stopMusic();
            long newTime = Duration.between(startTime, endTime).toSeconds();
            user.addTimeRoom1(newTime);
            startLayout2();
            startTime = Instant.now();
        }
        else if (currentLevel == 2) {
            layout1Controller.stopMusic();
            long newTime = Duration.between(startTime, endTime).toSeconds();
            user.addTimeRoom2(newTime);
            startLayout3();
            startTime = Instant.now();
        }
        else if (currentLevel == 3) {
            layout1Controller.stopMusic();
            long newTime = Duration.between(startTime, endTime).toSeconds();
            user.addTimeRoom3(newTime);

            // Set total game time at the end
            long totalTime = Duration.between(totalStartTime, endTime).toSeconds();
            user.setBestTime(Math.min(user.getBestTime(), totalTime)); // update bestTime

            user.updateAchievements();

            // The user has cleared all rooms
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Escape Room cleared! \n\n Clear Time: " + totalTime + " seconds",
                        ButtonType.OK);
                alert.setHeaderText(null);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("alert.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.showAndWait();
            });

            endGame();
        }
    }

    /**
     * Returns the SoundController for this game.
     *
     * @return The SoundController of the game
     */
    public SoundController getSoundController() {
        return soundController;
    }

    /**
     * Ends the game and switches back to the main menu scene.
     */
    @Override
    public void endGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
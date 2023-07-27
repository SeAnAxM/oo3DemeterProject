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

public class GameController {


    private Stage stage;
    private Scene mainMenuScene;
    private MainMenuController mainMenuController;
    private SoundController soundController;
    private Instant startTime, totalStartTime;
    private Instant endTime;
    private User user;

    private String username;
    private String appearance;
    public GameController(Stage stage) {
        this.stage = stage;
        this.soundController = SoundController.getInstance();
        this.currentLevel = 0; // Set the initial level
    }
    public void setUser(User user) {
        this.user = user;
    }


    // getter and setter for username and appearance

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    // Scenes for each layout
    private Scene layout1Scene;
    private Scene layout2Scene;
    private Scene layout3Scene;

    // Controllers for each layout
    private Layout1Controller layout1Controller;
    private Layout2Controller layout2Controller;
    private Layout3Controller layout3Controller;

    // Current game level
    private int currentLevel;


        public void startGame() throws IOException {

            this.setUser(UserSession.getInstance().getCurrentUser());

            // Initialize Layout1
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Layout1.fxml"));
            layout1Scene = new Scene(loader1.load());
            layout1Controller = loader1.getController();
            layout1Controller.setGameController(this); // Pass a reference to this GameController
            layout1Controller.setUsername(this.getUsername()); // Pass username
            layout1Controller.setAppearance(this.getAppearance());

            // Initialize Layout2
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Layout2.fxml"));
            layout2Scene = new Scene(loader2.load(),800,600);
            layout2Controller = loader2.getController();
            layout2Controller.setGameController(this); // Pass a reference to this GameController

            // Initialize Layout3
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Layout3.fxml"));
            layout3Scene = new Scene(loader3.load(),800,600);
            layout3Controller = loader3.getController();
            layout3Controller.setGameController(this); // Pass a reference to this GameController



            // Start the first game
        startLayout1();
        startTime = Instant.now();
        totalStartTime = Instant.now();
    }

    public void startLayout1() {
        stage.setScene(layout1Scene);
        layout1Controller.startGame();
        layout1Controller.startMusic();
        currentLevel = 1;
    }

    public void startLayout2() {
        // Check that the previous level was completed
        currentLevel = 1;
        if (currentLevel < 1) {
            // Maybe show an error message or something
            return;
        }

        stage.setScene(layout2Scene);
        layout2Controller.startGame();
        layout2Controller.startMusic();
        currentLevel = 2;
    }

    public void startLayout3() {
        // Check that the previous level was completed
        currentLevel = 2;
        if (currentLevel < 2) {
            // Maybe show an error message or something
            return;
        }

        stage.setScene(layout3Scene);
        layout3Controller.startGame();
        layout3Controller.startMusic();
        currentLevel = 3;
    }

    // Add similar methods for the other layouts...

    public void completeLevel() {
        // Fetch the user from UserSession
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

            // Return to the main menu
            exitToMainMenu();
        }
        else {
            // You might want to handle the case when currentLevel is not 1, 2, or 3
        }
    }

    public SoundController getSoundController() {
        return soundController;
    }

    public void exitToMainMenu() {
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
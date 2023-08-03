/*
 * Classname: StatsController.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class is a controller for the statistics view in the application. This class handles the display
 * of user's achievements and best times in the different rooms of the escape room game. It uses JavaFX's FXML
 * annotations to link the components of the user interface defined in an FXML document with their actions in the
 * program. The StatsController is responsible for initializing these components, loading the user's data, displaying
 * achievements, updating the best times labels, and handling the action of the exit button.
 */

package com.example.oo3demeterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the stats view. Shows the user's statistics, such as best times and achievements.
 */
public class StatsController {

    @FXML
    private ImageView achievement1Image, achievement2Image, achievement3Image, achievement4Image;

    @FXML
    private Label bestTimeLabel, bestTimeRoom1Label, bestTimeRoom2Label, bestTimeRoom3Label;

    @FXML
    private VBox achievementsBox, mainPane;
    @FXML
    private Button exitButton;


    // Assuming there is only one user for now
    private User user = UserSession.getInstance().getCurrentUser();

    /**
     * Called to initialize the controller after its root element has been
     * completely processed. It sets up the achievements and stats displays,
     * and adds a handler to the exit button.
     */
    @FXML
    public void initialize() {
        displayAchievements();
        displayStats();
        exitButton.setOnAction(event -> exitToMenu());
    }

    /**
     * Displays the achievements that the user has earned.
     */
    private void displayAchievements() {
        achievement1Image.setFitHeight(40);
        achievement1Image.setFitWidth(50);
        achievement1Image.setImage(new Image(getClass().getResource("silhouette.png").toString()));
        achievement2Image.setFitHeight(40);
        achievement2Image.setFitWidth(50);
        achievement2Image.setImage(new Image(getClass().getResource("silhouette.png").toString()));
        achievement3Image.setFitHeight(40);
        achievement3Image.setFitWidth(50);
        achievement3Image.setImage(new Image(getClass().getResource("silhouette.png").toString()));
        achievement4Image.setFitHeight(40);
        achievement4Image.setFitWidth(50);
        achievement4Image.setImage(new Image(getClass().getResource("silhouette.png").toString()));
        if (user.hasClearedEscapeRoom()) {
            achievement1Image.setImage(new Image(getClass().getResource("gold.png").toString()));
        }

        if (user.hasClearedEscapeRoomIn3Minutes()) {
            achievement2Image.setImage(new Image(getClass().getResource("bronze.png").toString()));
        }

        if (user.hasClearedEscapeRoomIn2Minutes()) {
            achievement3Image.setImage(new Image(getClass().getResource("silver.png").toString()));
        }

        if (user.hasClearedEscapeRoomIn1Minute()) {
            achievement4Image.setImage(new Image(getClass().getResource("gold.png").toString()));
        }
    }

    /**
     * Displays the user's best times.
     */
    private void displayStats() {
        String bestTime = (user.getBestTime() == Long.MAX_VALUE) ? "0" : String.valueOf(user.getBestTime());
        bestTimeLabel.setText("Best Time: " + bestTime + " seconds");
        String bestTimeRoom1 = (user.getBestTimeRoom1() == Long.MAX_VALUE) ? "0" : String.valueOf(user.getBestTimeRoom1());
        bestTimeRoom1Label.setText("Room 1: " + bestTimeRoom1 + " seconds");
        String bestTimeRoom2 = (user.getBestTimeRoom2() == Long.MAX_VALUE) ? "0" : String.valueOf(user.getBestTimeRoom2());
        bestTimeRoom2Label.setText("Room 2: " + bestTimeRoom2 + " seconds");
        String bestTimeRoom3 = (user.getBestTimeRoom3() == Long.MAX_VALUE) ? "0" : String.valueOf(user.getBestTimeRoom3());
        bestTimeRoom3Label.setText("Room 3: " + bestTimeRoom3+ " seconds");
    }

    /**
     * Exits to the main menu.
     */
    private void exitToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

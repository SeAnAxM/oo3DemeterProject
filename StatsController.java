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

    @FXML
    public void initialize() {
        displayAchievements();
        displayStats();
        exitButton.setOnAction(event -> exitToMenu());
    }

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

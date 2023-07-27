package com.example.oo3demeterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button startGameButton;

    private SoundController soundController;

    @FXML
    private Button viewStatsButton;

    @FXML
    private Button quitButton;

    @FXML
    private ImageView characterImageView;
    private User user;

    @FXML
    private Label welcomeLabel;

    private String username;
    private String appearance;

    public void setUsername(String username) {
        this.username = username;
        updateUI();
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
        updateUI();
    }

    private void updateUI() {
        if (appearance != null && username != null) {
            URL imageUrl = getClass().getResource("/com/example/oo3demeterproject/" + appearance.toLowerCase() + ".png");
            characterImageView.setImage(new Image(imageUrl.toString()));
            welcomeLabel.setText("Welcome " + username + "!");
        }
    }

    @FXML
    public void initialize() {
        soundController = SoundController.getInstance();
        UserSession session = UserSession.getInstance();
        this.username = session.getUsername();
        this.appearance = session.getAppearance();
        updateUI();
        soundController.playMainMenuMusic();
    }

    @FXML
    protected void onStartGameButtonClick() throws IOException {
        UserSession session = UserSession.getInstance();
        session.setUsername(username);
        session.setAppearance(appearance);

        user = session.getCurrentUser();
        if (user == null) {
            user = new User();
            session.setCurrentUser(user);
        }

        GameController gameController = new GameController((Stage) startGameButton.getScene().getWindow());

        gameController.setUser(user);

        // Start the game
        gameController.startGame();
    }

    @FXML
    protected void onViewStatsButtonClick() throws IOException {
        UserSession session = UserSession.getInstance();
        session.setUsername(username);
        session.setAppearance(appearance);
        user = session.getCurrentUser();
        if (user == null) {
            user = new User();
            session.setCurrentUser(user);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,800, 600);
        scene.getStylesheets().add(getClass().getResource("Stats.css").toExternalForm());
        // get the current stage and set the new scene
        Stage currentStage = (Stage) viewStatsButton.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }

    private void closeCurrentWindow() {
        Stage currentStage = (Stage) startGameButton.getScene().getWindow();
        currentStage.close();
    }
}

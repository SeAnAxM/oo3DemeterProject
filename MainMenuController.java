/*
 * Classname: MainMenuController.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class handles the logic and actions within the main menu interface of the application. This
 * includes managing the user interface interactions such as button clicks and dynamically updating the UI based
 * on user details, such as the user's chosen character image and username. This class is also responsible for
 * transitioning the user from the main menu to the game scene or the statistics page. Furthermore, it integrates
 * with the UserSession, GameController, and SoundController instances to ensure a consistent user experience
 * throughout the game. It also handles user session updates, game launching, and music control in the main menu.
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
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;

/**
 * Main Menu Controller for the application.
 * This class controls the UI interactions from the Main Menu.
 */
public class MainMenuController {

    @FXML
    private Button startGameButton;
    private SoundController soundController;
    @FXML
    private Button viewStatsButton;

    @FXML
    private ImageView characterImageView;
    private User user;

    @FXML
    private Label welcomeLabel;

    private String username;
    private String appearance;

    /**
     * Sets the username.
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
        updateUI();
    }

    /**
     * Sets the appearance.
     * @param appearance The appearance of the user.
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
        updateUI();
    }

    /**
     * Update UI elements.
     * This method changes the character image view and welcome label text.
     */
    private void updateUI() {
        if (appearance != null && username != null) {
            URL imageUrl = getClass().getResource("/com/example/oo3demeterproject/" + appearance.toLowerCase() + ".png");
            characterImageView.setImage(new Image(imageUrl.toString()));
            welcomeLabel.setText("Welcome " + username + "!");
        }
    }

    /**
     * Initialize the MainMenuController.
     * This method is called after all @FXML annotated members have been injected.
     */
    @FXML
    public void initialize() {
        soundController = SoundController.getInstance();
        UserSession session = UserSession.getInstance();
        this.username = session.getUsername();
        this.appearance = session.getAppearance();
        updateUI();
        soundController.playMainMenuMusic();
    }

    /**
     * Called when the 'Start Game' button is clicked.
     * This method starts the game.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Called when the 'View Stats' button is clicked.
     * This method navigates to the statistics page.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Called when the 'Quit' button is clicked.
     * This method quits the application.
     */
    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }
}

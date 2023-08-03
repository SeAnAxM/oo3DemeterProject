/*
 * Classname: Layout3Controller.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class, an extension of the LayoutController, is responsible for managing the third level layout
 * of a card-matching game. It includes specific functions for card shuffling, matching logic, transitioning to the
 * main menu, and managing the game's completion. Additionally, this class controls the game's interface elements,
 * such as card images, layout styles, and the exit button functionality. This class also includes time tracking
 * functionalities to record the time spent on the level and updates the user's best time for this level. Music for
 * the game level can also be controlled by this class.
 */


package com.example.oo3demeterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Layout3Controller class that extends the LayoutController.
 * It represents a specific layout (Layout 3) for the game UI and includes game logic specific to this layout.
 * This class manages game elements such as card shuffling, matching logic, game completion, and transitioning to the main menu.
 */
public class Layout3Controller extends LayoutController{
    @FXML private GridPane gridPane;

    @FXML
    private AnchorPane rootLayout;

    private final String[] cards = {"genderequality1.png", "genderequality2.png", "lifeonland1.png", "lifeonland2.png", "climateaction1.png", "climateaction2.png"};
    private final List<String> deck = new ArrayList<>(Arrays.asList(cards));
    private User user;
    private long startTime;
    private Button firstCard;
    private Button secondCard;
    private GameController gameController;
    @FXML
    private Button exitButton;
    @FXML
    private Label matchStatusLabel;


    private final Image cardBackImage = new Image(getClass().getResource("/assets/back_card.png").toExternalForm());

    /**
     * Method to set the game controller instance in the layout.
     *
     * @param gameController GameController instance to be set in the layout.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Overrides the initialize method of the parent class.
     * It sets the style for the layout, shuffles the card deck, assigns images to the card buttons,
     * and sets the exit button's action to return to the main menu.
     */
    @Override
    public void initialize() {
        rootLayout.getStylesheets().add(getClass().getResource("layout3.css").toExternalForm());
        Collections.shuffle(deck); // Shuffle the deck for randomness
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int index = columnIndex + rowIndex * 2;

                String imageUrl = "/assets/" + deck.get(index);
                String imageIdentifier = imageUrl.substring(0, imageUrl.length() - 5);
                Card card = new Card(imageIdentifier);
                node.setUserData(card);

                ImageView imageView = new ImageView(cardBackImage);
                imageView.setFitWidth(125);
                imageView.setFitHeight(125);
                ((Button) node).setGraphic(imageView);
            }
        }
        exitButton.setOnAction(event -> exitToMenu());
    }

    /**
     * Returns the current game number.
     *
     * @return An integer representing the game number.
     */
    public int getGameNumber() {
        return 3;
    }

    /**
     * Handles the card flipping action event.
     *
     * @param event ActionEvent instance representing the current event.
     */
    @FXML
    private void flipCard(ActionEvent event) {
        Button clickedCard = (Button) event.getSource();
        int columnIndex = GridPane.getColumnIndex(clickedCard) == null ? 0 : GridPane.getColumnIndex(clickedCard);
        int rowIndex = GridPane.getRowIndex(clickedCard) == null ? 0 : GridPane.getRowIndex(clickedCard);
        int index = columnIndex + rowIndex * 2; // Adjust this if gridPane has more than 2 columns

        String imageUrl = "/assets/" + deck.get(index);
        Image cardImage = new Image(getClass().getResource(imageUrl).toExternalForm());
        ImageView imageView = new ImageView(cardImage);
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        clickedCard.setGraphic(imageView);

        if (firstCard == null) {
            firstCard = clickedCard;
        } else if (secondCard == null) {
            secondCard = clickedCard;
            checkForMatch();
        }
    }

    /**
     * Checks if the selected cards match.
     */
    private void checkForMatch() {
        Card firstCardData = (Card) firstCard.getUserData();
        Card secondCardData = (Card) secondCard.getUserData();

        if (firstCardData.getImageIdentifier().equals(secondCardData.getImageIdentifier())) {
            matchStatusLabel.setText("Matched");
            firstCardData.setMatched(true);
            secondCardData.setMatched(true);
            firstCard = null;
            secondCard = null;
            checkGameFinished();
        } else {
            matchStatusLabel.setText("Not matched");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                firstCardData.flip();
                secondCardData.flip();

                ImageView backImage1 = new ImageView(cardBackImage);
                backImage1.setFitWidth(125);
                backImage1.setFitHeight(125);

                ImageView backImage2 = new ImageView(cardBackImage);
                backImage2.setFitWidth(125);
                backImage2.setFitHeight(125);

                firstCard.setGraphic(backImage1);
                secondCard.setGraphic(backImage2);
                firstCard = null;
                secondCard = null;
            });
            pause.play();
        }
    }

    /**
     * Checks if the game has finished by verifying if all cards have been matched.
     */
    private void checkGameFinished() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Card card = (Card) node.getUserData();
                if (!card.isMatched()) {
                    return;
                }
            }
        }
        levelCompleted();
    }

    /**
     * Begins the game, initializing all necessary values and starting the timer.
     */
    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
        System.out.println("Timer for Layout3 started");
    }

    /**
     * Completes the current level, calculates and records the time spent,
     * then transitions to the next level.
     */
    public void levelCompleted() {
        long endTime = System.currentTimeMillis();
        System.out.println("Timer for Layout3 stopped");

        long timeSpent = endTime - this.startTime;
        System.out.println("Time spent in Layout3: " + timeSpent + " milliseconds");

        User currentUser = UserSession.getInstance().getCurrentUser();
        currentUser.setBestTimeRoom3(Math.min(currentUser.getBestTimeRoom3(), timeSpent));

        gameController.getSoundController().playRoomClearSound();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> gameController.completeLevel());
        pause.play();
    }

    /**
     * Handles the action of exiting to the main menu.
     */
    private void exitToMenu() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            UserSession session = UserSession.getInstance();
            mainMenuController.setUsername(session.getUsername());
            mainMenuController.setAppearance(session.getAppearance());

            user = session.getCurrentUser();
            if (user == null) {
                user = new User();
                session.setCurrentUser(user);
            }

            user.removeRecentTimeRoom1();
            user.removeRecentTimeRoom2();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
            Stage stage = (Stage) rootLayout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game music for this layout.
     */
    public void startMusic() {
        gameController.getSoundController().playGameMusic(getGameNumber());
    }

    /**
     * Stops the game music for this layout.
     */
    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }
}
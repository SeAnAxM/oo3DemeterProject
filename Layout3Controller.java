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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Layout3Controller {
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

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    private void initialize() {
        rootLayout.getStylesheets().add(getClass().getResource("layout3.css").toExternalForm());
        Collections.shuffle(deck); // Shuffle the deck for randomness
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                int columnIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int index = columnIndex + rowIndex * 2;

                String imageUrl = "/assets/" + deck.get(index);
                String imageIdentifier = imageUrl.substring(0, imageUrl.length() - 5); // remove "1.png" or "2.png"
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

        // Note: We're not setting userData here, as it's already set to a Card instance in the initialize method.

        if (firstCard == null) {
            firstCard = clickedCard;
        } else if (secondCard == null) {
            secondCard = clickedCard;
            checkForMatch();
        }
    }

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

    private void checkGameFinished() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Card card = (Card) node.getUserData();
                if (!card.isMatched()) {
                    return; // There are still cards not matched
                }
            }
        }
        levelCompleted();
    }
    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
        System.out.println("Timer for Layout3 started");
    }

    public void levelCompleted() {
        long endTime = System.currentTimeMillis();
        System.out.println("Timer for Layout3 stopped");

        long timeSpent = endTime - this.startTime;
        System.out.println("Time spent in Layout3: " + timeSpent + " milliseconds");

        User currentUser = UserSession.getInstance().getCurrentUser(); // Get the current User
        currentUser.setBestTimeRoom3(Math.min(currentUser.getBestTimeRoom3(), timeSpent));

        gameController.getSoundController().playRoomClearSound();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> gameController.completeLevel());
        pause.play();
    }

    private void exitToMenu() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            UserSession session = UserSession.getInstance();
            mainMenuController.setUsername(session.getUsername()); // Pass username
            mainMenuController.setAppearance(session.getAppearance()); // Pass appearance

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

    public void startMusic() {
        gameController.getSoundController().playGame3Music();
    }

    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }
}
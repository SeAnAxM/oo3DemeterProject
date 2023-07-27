package com.example.oo3demeterproject;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.*;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Layout1Controller {

    @FXML
    private ImageView plasticBin, glassBin, paperBin, organicBin;
    @FXML
    private StackPane mainPane;
    @FXML
    private Pane stackPane;


    private MediaPlayer mediaPlayer;
    private long startTime;
    @FXML
    private Button exitButton;
    private GameController gameController;
    @FXML
    private Label scoreLabel;


    private String username;
    private String appearance;
    private int correctWasteCount = 0;

    private static final String[] WASTE_IMAGES = {"paper1.png", "paper2.png", "organic1.png", "organic2.png", "glass1.png", "glass2.png", "plastic1.png", "plastic2.png"};

    private int[] wasteCount = new int[4];

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private int totalWasteCount = 0;

    private int getTotalWasteCount() {
        return totalWasteCount;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }


    public void initialize() {
        mainPane.getStylesheets().add(getClass().getResource("layout1.css").toExternalForm());
        Timeline wasteFallingTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> spawnWaste()));
        wasteFallingTimeline.setCycleCount(Timeline.INDEFINITE);
        wasteFallingTimeline.play();
        updateScoreLabel();
        exitButton.setOnAction(event -> exitToMenu());
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Wastes correctly placed: " + correctWasteCount + "/15");
    }
    private void spawnWaste() {
        if (getTotalWasteCount() >= 16) {
            return;
        }

        int wasteType = (int) (Math.random() * WASTE_IMAGES.length / 2);
        if (wasteCount[wasteType] >= 2) {
            return;
        }

        String wasteImageName = WASTE_IMAGES[wasteType * 2 + (Math.random() < 0.5 ? 0 : 1)];
        wasteCount[wasteType]++;

        ImageView waste = new ImageView(new Image(getClass().getResource("/assets/" + wasteImageName).toString()));
        waste.setFitWidth(80);
        waste.setFitHeight(80);

        waste.setLayoutX(Math.random() * 100 + (stackPane.getWidth() / 2 - 50)); // Vary around the middle 100px
        waste.setLayoutY(-50);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), waste);
        transition.setByY(mainPane.getHeight());
        transition.setCycleCount(Animation.INDEFINITE);

        transition.setOnFinished(event -> {
            if (waste.getLayoutY() + waste.getTranslateY() >= mainPane.getHeight()) {
                // Waste has reached the bottom, so reset its position
                waste.setLayoutY(-50);
                waste.setTranslateY(0);
                waste.setTranslateX(Math.random() * 100 + (stackPane.getWidth() / 2 - 50)); // Random horizontal position
                transition.playFromStart();
            }
        });


        waste.setOnMousePressed(event -> {
            transition.stop(); // Stop the falling animation when waste is picked up
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            orgTranslateX = waste.getTranslateX();
            orgTranslateY = waste.getTranslateY();
        });

        waste.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            waste.setTranslateX(newTranslateX);
            waste.setTranslateY(newTranslateY);
        });

        waste.setOnMouseReleased(event -> {
            if (checkCorrectBin(waste, wasteImageName)) {
                stackPane.getChildren().remove(waste);
                wasteCount[wasteType]--;
                totalWasteCount--;
                correctWasteCount++;
                updateScoreLabel();
                if (correctWasteCount >= 15) {
                    levelCompleted();
                }
            } else {
                waste.setLayoutX(Math.random() * (stackPane.getWidth() - waste.getFitWidth())); // Set to random starting position
                waste.setLayoutY(-50);
                waste.setTranslateX(0);
                waste.setTranslateY(0);
                transition.playFromStart(); // Restart the falling animation if waste is not placed in a correct bin
            }
            event.consume();
        });

        transition.play();
        stackPane.getChildren().add(waste);
        totalWasteCount++;
    }

    private boolean checkCorrectBin(ImageView waste, String wasteImageName) {
        Bounds wasteInStackPane = stackPane.sceneToLocal(waste.localToScene(waste.getBoundsInLocal()));
        Bounds paperBinInStackPane = stackPane.sceneToLocal(paperBin.localToScene(paperBin.getBoundsInLocal()));
        Bounds organicBinInStackPane = stackPane.sceneToLocal(organicBin.localToScene(organicBin.getBoundsInLocal()));
        Bounds glassBinInStackPane = stackPane.sceneToLocal(glassBin.localToScene(glassBin.getBoundsInLocal()));
        Bounds plasticBinInStackPane = stackPane.sceneToLocal(plasticBin.localToScene(plasticBin.getBoundsInLocal()));

        if (wasteImageName.startsWith("paper") && wasteInStackPane.intersects(paperBinInStackPane)) {
            System.out.println("Paper waste placed in the paper bin.");
            return true;
        }
        if (wasteImageName.startsWith("organic") && wasteInStackPane.intersects(organicBinInStackPane)) {
            System.out.println("Organic waste placed in the organic bin.");
            return true;
        }
        if (wasteImageName.startsWith("glass") && wasteInStackPane.intersects(glassBinInStackPane)) {
            System.out.println("Glass waste placed in the glass bin.");
            return true;
        }
        if (wasteImageName.startsWith("plastic") && wasteInStackPane.intersects(plasticBinInStackPane)) {
            System.out.println("Plastic waste placed in the plastic bin.");
            return true;
        }
        return false;
    }

    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
        System.out.println("Timer for Layout1 started");

    }

    public void levelCompleted() {
        long endTime = System.currentTimeMillis();
        System.out.println("Timer for Layout1 stopped");

        long timeSpent = endTime - this.startTime;
        System.out.println("Time spent in Layout1: " + timeSpent + " milliseconds");

        User currentUser = UserSession.getInstance().getCurrentUser(); // Get the current User
        currentUser.setBestTimeRoom1(Math.min(currentUser.getBestTimeRoom1(), timeSpent));

        gameController.getSoundController().playRoomClearSound();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> gameController.completeLevel());
        pause.play();
    }

    private void exitToMenu() {
        // Clean up game state
        totalWasteCount = 0;
        correctWasteCount = 0;
        wasteCount = new int[4];
        stackPane.getChildren().clear();
        updateScoreLabel();

        // Switch back to the main menu
        // This will depend on your specific application structure
        // As an example, assuming you have a separate "MenuController" class:
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            UserSession session = UserSession.getInstance();
            mainMenuController.setUsername(session.getUsername()); // Pass username
            mainMenuController.setAppearance(session.getAppearance()); // Pass appearance

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMusic() {
        gameController.getSoundController().playGame1Music();
    }

    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }
}

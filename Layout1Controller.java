/*
 * Classname: Layout1Controller.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class is responsible for controlling the layout of the first level in a waste management game.
 * It extends the LayoutController class and includes functionalities for initializing the layout, spawning different
 * types of waste, checking if the waste has been placed in the correct bin, updating the score label, and handling
 * actions for exiting to the main menu, starting the game, completing the level, starting and stopping music. It uses
 * JavaFX to create an interactive user interface.
 */


package com.example.oo3demeterproject;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.*;
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

/**
 * Class responsible for controlling the layout of a level in the game, extending the LayoutController class.
 * Includes methods for initializing the layout, spawning waste, checking bin correctness, and exiting to the menu, among others.
 */
public class Layout1Controller extends LayoutController{

    @FXML
    private ImageView plasticBin, glassBin, paperBin, organicBin;
    @FXML
    private StackPane mainPane;
    @FXML
    private Pane stackPane;
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

    /**
     * Method that gets the total count of waste
     * @return The total count of waste
     */
    private int getTotalWasteCount() {
        return totalWasteCount;
    }

    /**
     * Method that returns game number
     * @return The number of the game
     */
    @Override
    public int getGameNumber() {
        return 1;
    }

    /**
     * Method to set the username
     * @param username The username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to set the appearance
     * @param appearance The appearance to be set
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    /**
     * Method to set the game controller
     * @param gameController The game controller to be set
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Method to initialize the layout
     */
    @Override
    public void initialize() {
        mainPane.getStylesheets().add(getClass().getResource("layout1.css").toExternalForm());
        Timeline wasteFallingTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> spawnWaste()));
        wasteFallingTimeline.setCycleCount(Timeline.INDEFINITE);
        wasteFallingTimeline.play();
        updateScoreLabel();
        exitButton.setOnAction(event -> exitToMenu());
    }

    /**
     * Method to update the score label
     */
    private void updateScoreLabel() {
        scoreLabel.setText("Wastes correctly placed: " + correctWasteCount + "/15");
    }

    /**
     * Method to spawn waste
     */
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

        waste.setLayoutX(Math.random() * 100 + (stackPane.getWidth() / 2 - 50));
        waste.setLayoutY(-50);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), waste);
        transition.setByY(mainPane.getHeight());
        transition.setCycleCount(Animation.INDEFINITE);

        transition.setOnFinished(event -> {
            if (waste.getLayoutY() + waste.getTranslateY() >= mainPane.getHeight()) {
                waste.setLayoutY(-50);
                waste.setTranslateY(0);
                waste.setTranslateX(Math.random() * 100 + (stackPane.getWidth() / 2 - 50));
                transition.playFromStart();
            }
        });

        waste.setOnMousePressed(event -> {
            transition.stop();
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
                waste.setLayoutX(Math.random() * (stackPane.getWidth() - waste.getFitWidth()));
                waste.setLayoutY(-50);
                waste.setTranslateX(0);
                waste.setTranslateY(0);
                transition.playFromStart();
            }
            event.consume();
        });

        transition.play();
        stackPane.getChildren().add(waste);
        totalWasteCount++;
    }

    /**
     * Method to check if the waste is in the correct bin
     * @param waste The waste to check
     * @param wasteImageName The name of the waste image
     * @return true if waste is in correct bin, false otherwise
     */
    private boolean checkCorrectBin(ImageView waste, String wasteImageName) {
        Bounds wasteInStackPane = stackPane.sceneToLocal(waste.localToScene(waste.getBoundsInLocal()));
        Bounds paperBinInStackPane = stackPane.sceneToLocal(paperBin.localToScene(paperBin.getBoundsInLocal()));
        Bounds organicBinInStackPane = stackPane.sceneToLocal(organicBin.localToScene(organicBin.getBoundsInLocal()));
        Bounds glassBinInStackPane = stackPane.sceneToLocal(glassBin.localToScene(glassBin.getBoundsInLocal()));
        Bounds plasticBinInStackPane = stackPane.sceneToLocal(plasticBin.localToScene(plasticBin.getBoundsInLocal()));

        if (wasteImageName.startsWith("paper") && wasteInStackPane.intersects(paperBinInStackPane)) {
            return true;
        }
        if (wasteImageName.startsWith("organic") && wasteInStackPane.intersects(organicBinInStackPane)) {
            return true;
        }
        if (wasteImageName.startsWith("glass") && wasteInStackPane.intersects(glassBinInStackPane)) {
            return true;
        }
        if (wasteImageName.startsWith("plastic") && wasteInStackPane.intersects(plasticBinInStackPane)) {
            return true;
        }
        return false;
    }

    /**
     * Method to start the game
     */
    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
    }

    /**
     * Method to be called when a level is completed
     */
    public void levelCompleted() {
        long endTime = System.currentTimeMillis();

        long timeSpent = endTime - this.startTime;

        User currentUser = UserSession.getInstance().getCurrentUser();
        currentUser.setBestTimeRoom1(Math.min(currentUser.getBestTimeRoom1(), timeSpent));

        gameController.getSoundController().playRoomClearSound();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> gameController.completeLevel());
        pause.play();
    }

    /**
     * Method to exit to menu
     */
    private void exitToMenu() {
        totalWasteCount = 0;
        correctWasteCount = 0;
        wasteCount = new int[4];
        stackPane.getChildren().clear();
        updateScoreLabel();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            UserSession session = UserSession.getInstance();
            mainMenuController.setUsername(session.getUsername());
            mainMenuController.setAppearance(session.getAppearance());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to start the music
     */
    public void startMusic() {
        gameController.getSoundController().playGameMusic(getGameNumber());
    }

    /**
     * Method to stop the music
     */
    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }
}

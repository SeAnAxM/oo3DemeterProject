package com.example.oo3demeterproject;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;



public class Layout2Controller {
    private String password;
    private List<Character> passwordLetters;
    private boolean[] answeredCorrectly = new boolean[5];
    @FXML
    private AnchorPane rootLayout;

    private MediaPlayer mediaPlayer;

    @FXML
    private Pane mainPane, questionPane1, questionPane2, questionPane3, questionPane4, questionPane5;

    @FXML
    private Button backButton1, backButton2, backButton3, backButton4, backButton5, submitButton, passwordSubmit, exitButton;

    @FXML
    private Label correctAnswer, wrongAnswer, correctCode, wrongCode, hintLabel, timerLabel1, timerLabel2, timerLabel3, timerLabel4, timerLabel5;

    @FXML
    private RadioButton choiceA1, choiceB1, choiceC1, choiceD1, choiceA2, choiceB2, choiceC2, choiceD2;

    long startTime;

    private GameController gameController;

    @FXML
    private TextField answerField3, answerField4, answerField5, passwordField;
    @FXML
    private ImageView pic1, pic2, pic3, pic4;

    private ToggleGroup toggleGroup1, toggleGroup2;
    private User user;
    private String username;
    private String appearance;
    private final String[] correctAnswers = {"17", "Eradicate hunger, secure food, improve nutrition, and boost sustainable farming.", "development", "climate change", "education"};

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void startGame() {
        initialize();
        startTime = System.currentTimeMillis();
        System.out.println("Timer for Layout2 started");

    }

    @FXML
    public void initialize() {

        rootLayout.getStylesheets().add(getClass().getResource("layout2.css").toExternalForm());
        exitButton.setVisible(true);
        this.password = getPasswordFromFile("words.txt");
        this.passwordLetters = password.chars().mapToObj(c -> (char)c).collect(Collectors.toList());


        toggleGroup1 = new ToggleGroup();
        choiceA1.setToggleGroup(toggleGroup1);
        choiceB1.setToggleGroup(toggleGroup1);
        choiceC1.setToggleGroup(toggleGroup1);
        choiceD1.setToggleGroup(toggleGroup1);

        toggleGroup2 = new ToggleGroup();
        choiceA2.setToggleGroup(toggleGroup2);
        choiceB2.setToggleGroup(toggleGroup2);
        choiceC2.setToggleGroup(toggleGroup2);
        choiceD2.setToggleGroup(toggleGroup2);

        questionPane1.setVisible(false);
        submitButton.setVisible(false);
        passwordField.setVisible(false);
        passwordSubmit.setVisible(false);

        backButton1.setOnAction(event -> {
            mainPane.setVisible(true);
            questionPane1.setVisible(false);
            submitButton.setVisible(false);
        });

        backButton2.setOnAction(event -> {
            mainPane.setVisible(true);
            questionPane2.setVisible(false);
            submitButton.setVisible(false);
        });

        backButton3.setOnAction(event -> {
            mainPane.setVisible(true);
            questionPane3.setVisible(false);
            submitButton.setVisible(false);
        });

        backButton4.setOnAction(event -> {
            mainPane.setVisible(true);
            questionPane4.setVisible(false);
            submitButton.setVisible(false);
        });

        backButton5.setOnAction(event -> {
            mainPane.setVisible(true);
            questionPane5.setVisible(false);
            submitButton.setVisible(false);
        });

        exitButton.setOnAction(event -> exitToMenu());
    }

    @FXML
    public void showQuestion1(ActionEvent event) {
        if(answeredCorrectly[0]) {
            correctAnswer.setText("You answered correctly, proceed with the next question!");
            correctAnswer.setText("");
            correctCode.setText("");
            wrongAnswer.setText("");
            wrongCode.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            return;
        }
        mainPane.setVisible(false);
        questionPane1.setVisible(true);
        submitButton.setVisible(true);
    }
    @FXML
    public void showQuestion2(ActionEvent event) {
        if(answeredCorrectly[1]) {
            correctAnswer.setText("You answered correctly, proceed with the next question!");
            correctCode.setText("");
            wrongAnswer.setText("");
            wrongCode.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            return;
        }
        mainPane.setVisible(false);
        questionPane2.setVisible(true);
        submitButton.setVisible(true);
    }
    @FXML
    public void showQuestion3(ActionEvent event) {
        if(answeredCorrectly[2]) {
            correctAnswer.setText("You answered correctly, proceed with the next question!");
            correctCode.setText("");
            wrongAnswer.setText("");
            wrongCode.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            return;
        }
        mainPane.setVisible(false);
        questionPane3.setVisible(true);
        submitButton.setVisible(true);
    }
    @FXML
    public void showQuestion4(ActionEvent event) {
        if(answeredCorrectly[3]) {
            correctAnswer.setText("You answered correctly, proceed with the next question!");
            correctCode.setText("");
            wrongAnswer.setText("");
            wrongCode.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            return;
        }
        mainPane.setVisible(false);
        questionPane4.setVisible(true);
        submitButton.setVisible(true);
    }
    @FXML
    public void showQuestion5(ActionEvent event) {
        if(answeredCorrectly[4]) {
            correctAnswer.setText("You answered correctly, proceed with the next question!");
            correctCode.setText("");
            wrongAnswer.setText("");
            wrongCode.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            return;
        }
        mainPane.setVisible(false);
        questionPane5.setVisible(true);
        submitButton.setVisible(true);

        pic1.setFitWidth(100);
        pic1.setFitHeight(100);
        pic1.setPreserveRatio(true);

        pic2.setFitWidth(100);
        pic2.setFitHeight(100);
        pic2.setPreserveRatio(true);

        pic3.setFitWidth(100);
        pic3.setFitHeight(100);
        pic3.setPreserveRatio(true);

        pic4.setFitWidth(100);
        pic4.setFitHeight(100);
        pic4.setPreserveRatio(true);
    }


    @FXML
    public void submitAnswers() {
        if(questionPane1.isVisible() && !answeredCorrectly[0]){
            RadioButton selectedRadioButton1 = (RadioButton) toggleGroup1.getSelectedToggle();
            if (selectedRadioButton1 != null) {
                String answer1 = selectedRadioButton1.getText();
                if (answer1.equals(correctAnswers[0])) {
                    questionPane1.setVisible(false);
                    mainPane.setVisible(true);
                    submitButton.setVisible(false);
                    correctAnswer.setText("Correct answer! Proceed with the next question.");
                    correctAnswer.getStyleClass().add("correct-answer");
                    if(!passwordLetters.isEmpty()){
                        int randomIndex = new Random().nextInt(passwordLetters.size());
                        char hint = passwordLetters.get(randomIndex);
                        passwordLetters.remove(randomIndex);
                        hintLabel.setText(hintLabel.getText() + hint);
                    }
                    wrongAnswer.setText("");
                    wrongCode.setText("");
                    answeredCorrectly[0] = true;
                } else {
                    mainPane.setVisible(true);
                    correctAnswer.setText("");
                    wrongAnswer.setText("Wrong answer, Try Again!");
                    wrongCode.setText("");
                    wrongAnswer.getStyleClass().add("wrong-answer");
                    questionPane1.setVisible(false);
                    submitButton.setVisible(false);
                }
            }
        }
        if(questionPane2.isVisible() && !answeredCorrectly[1]){
            RadioButton selectedRadioButton2 = (RadioButton) toggleGroup2.getSelectedToggle();
            if (selectedRadioButton2 != null) {
                String answer2 = selectedRadioButton2.getText();
                if (answer2.equals(correctAnswers[1])) {
                    questionPane2.setVisible(false);
                    mainPane.setVisible(true);
                    submitButton.setVisible(false);
                    correctAnswer.setText("Correct answer! Proceed with the next question.");
                    correctAnswer.getStyleClass().add("correct-answer");
                    if(!passwordLetters.isEmpty()){
                        int randomIndex = new Random().nextInt(passwordLetters.size());
                        char hint = passwordLetters.get(randomIndex);
                        passwordLetters.remove(randomIndex);
                        hintLabel.setText(hintLabel.getText() + hint);
                    }
                    wrongAnswer.setText("");
                    wrongCode.setText("");
                    answeredCorrectly[1] = true;
                } else {
                    mainPane.setVisible(true);
                    correctAnswer.setText("");
                    wrongAnswer.setText("Wrong answer, Try Again!");
                    wrongAnswer.getStyleClass().add("wrong-answer");
                    questionPane2.setVisible(false);
                    wrongCode.setText("");
                    submitButton.setVisible(false);
                }
            }
        }
        if(questionPane3.isVisible() && !answeredCorrectly[2]){
            String answer3 = answerField3.getText().toLowerCase();
            if (answer3.equals(correctAnswers[2])) {
                questionPane3.setVisible(false);
                mainPane.setVisible(true);
                submitButton.setVisible(false);
                correctAnswer.setText("Correct answer! Proceed with the next question.");
                correctAnswer.getStyleClass().add("correct-answer");
                if(!passwordLetters.isEmpty()){
                    int randomIndex = new Random().nextInt(passwordLetters.size());
                    char hint = passwordLetters.get(randomIndex);
                    passwordLetters.remove(randomIndex);
                    hintLabel.setText(hintLabel.getText() + hint);
                }
                wrongAnswer.setText("");
                wrongCode.setText("");
                answeredCorrectly[2] = true;
            } else {
                mainPane.setVisible(true);
                wrongCode.setText("");
                correctAnswer.setText("");
                wrongAnswer.setText("Wrong answer, Try Again!");
                wrongAnswer.getStyleClass().add("wrong-answer");
                questionPane3.setVisible(false);
                submitButton.setVisible(false);
            }
        }
        if(questionPane4.isVisible() && !answeredCorrectly[3]){
            String answer4 = answerField4.getText().toLowerCase();
            if (answer4.equals(correctAnswers[3])) {
                questionPane4.setVisible(false);
                mainPane.setVisible(true);
                submitButton.setVisible(false);
                correctAnswer.setText("Correct answer! Proceed with the next question.");
                correctAnswer.getStyleClass().add("correct-answer");
                if(!passwordLetters.isEmpty()){
                    int randomIndex = new Random().nextInt(passwordLetters.size());
                    char hint = passwordLetters.get(randomIndex);
                    passwordLetters.remove(randomIndex);
                    hintLabel.setText(hintLabel.getText() + hint);
                }
                wrongAnswer.setText("");
                wrongCode.setText("");
                answeredCorrectly[3] = true;
            } else {
                mainPane.setVisible(true);
                correctAnswer.setText("");
                wrongAnswer.setText("Wrong answer, Try Again!");
                wrongCode.setText("");
                wrongAnswer.getStyleClass().add("wrong-answer");
                questionPane4.setVisible(false);
                submitButton.setVisible(false);
            }
        }
        if(questionPane5.isVisible() && !answeredCorrectly[4]){
            String answer5 = answerField5.getText().toLowerCase();
            if (answer5.equals(correctAnswers[4])) {
                mainPane.setVisible(true);
                submitButton.setVisible(false);
                questionPane5.setVisible(false);
                correctAnswer.setText("Correct answer! Proceed with the next question.");
                correctAnswer.getStyleClass().add("correct-answer");
                if(!passwordLetters.isEmpty()){
                    int randomIndex = new Random().nextInt(passwordLetters.size());
                    char hint = passwordLetters.get(randomIndex);
                    passwordLetters.remove(randomIndex);
                    hintLabel.setText(hintLabel.getText() + hint);
                }
                wrongAnswer.setText("");
                wrongCode.setText("");
                answeredCorrectly[4] = true;
            } else {
                mainPane.setVisible(true);
                correctAnswer.setText("");
                wrongAnswer.setText("Wrong answer, Try Again!");
                wrongCode.setText("");
                wrongAnswer.getStyleClass().add("wrong-answer");
                questionPane5.setVisible(false);
                submitButton.setVisible(false);
            }
        }
    }

    private String getPasswordFromFile(String fileName) {
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            if (in == null) {
                throw new FileNotFoundException("Resource not found:" + fileName);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            List<String> lines = reader.lines().collect(Collectors.toList());
            Random rand = new Random();
            return lines.get(rand.nextInt(lines.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    @FXML
    public void submitPassword() {
        String enteredPassword = passwordField.getText();
        passwordField.clear();
        passwordField.setVisible(false);
        passwordSubmit.setVisible(false);
        if (password.equals(enteredPassword)) {
            // password correct, go to layout3
            correctCode.setText("Correct answer, proceeding to part 3!");
            correctAnswer.setText("");
            correctAnswer.getStyleClass().add("correct-answer");
            wrongCode.setText("");
            levelCompleted();
        } else {
            wrongCode.setText("Wrong answer, try again!");
            correctAnswer.setText("");
            wrongAnswer.setText("");
            wrongAnswer.getStyleClass().add("wrong-answer");
            correctCode.setText("");
        }
    }

    @FXML
    private void togglePasswordField() {
        boolean isVisible = passwordField.isVisible();
        passwordField.setVisible(!isVisible);
        passwordSubmit.setVisible(!isVisible);
    }

    public void levelCompleted() {
        long endTime = System.currentTimeMillis();
        System.out.println("Timer for Layout2 stopped");

        long timeSpent = endTime - this.startTime;
        System.out.println("Time spent in Layout2: " + timeSpent + " milliseconds");

        User currentUser = UserSession.getInstance().getCurrentUser(); // Get the current User
        currentUser.setBestTimeRoom2(Math.min(currentUser.getBestTimeRoom2(), timeSpent));

        gameController.getSoundController().playRoomClearSound();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> gameController.completeLevel());
        pause.play();
    }

    private void exitToMenu() {
        rootLayout.getChildren().clear();


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

            user = session.getCurrentUser();
            if (user == null) {
                user = new User();
                session.setCurrentUser(user);
            }

            user.removeRecentTimeRoom1();

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
        gameController.getSoundController().playGame2Music();
    }

    public void stopMusic() {
        gameController.getSoundController().stopSound();
    }

}

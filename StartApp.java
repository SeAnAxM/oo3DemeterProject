/*
 * Classname: StarApp.java
 *
 * Author: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: This class serves as the main entry point for this JavaFX application. It's responsible for setting up
 * the primary stage and loading the initial scene (Character Setup) upon launching the application. The class extends
 * the javafx.application.Application class, overwriting the start() method to define the application's initial user
 * interface. It also includes a main method that can be used to launch the application in environments with limited
 * JavaFX support.
 */

package com.example.oo3demeterproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main application class that launches the game. It extends the javafx.application.Application
 * class, which means it acts as the main entry point for all JavaFX applications.
 */
public class StartApp extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the system is ready for the
     * application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if loading the FXML file fails.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Chasing Change: The Escape Room for World Changers");

        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("CharacterSetup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("charactersetup.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can't be launched through deployment artifacts, e.g., in IDEs with limited FX support.
     * It can be removed if deployment artifacts (like JAR or native bundlers) are correctly working.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

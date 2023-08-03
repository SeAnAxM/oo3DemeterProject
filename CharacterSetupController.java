/*
 * Classname: CharacterSetupController.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The CharacterSetupController class manages the character setup phase of a game. It handles UI
 * interactions for character selection and transitions to the main menu scene after character selection. It also
 * allows players to input their username. The class utilizes JavaFX for UI interactions and elements.
 */


package com.example.oo3demeterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.effect.Glow;

/**
 * CharacterSetupController class for managing the Character Setup scene.
 * It handles user inputs for character selection and transition to the main menu scene.
 */
public class CharacterSetupController {

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView businessmanImage, chefImage, doctorImage;

    @FXML
    private Button submitButton;

    private String appearance;

    /**
     * Initializes the CharacterSetupController.
     * Sets up mouse click events for image views of characters to handle character selection,
     * and adds a glow effect to the selected character's image.
     */
    @FXML
    public void initialize() {
        Glow glow = new Glow();
        glow.setLevel(0.8);  // level between 0.0 to 1.0, where 1.0 is maximum glow

        businessmanImage.setOnMouseClicked(e -> {
            selectImage("Businessman");
            // Set the glow effect to the selected image
            businessmanImage.setEffect(glow);
            // Remove the effect from the other images
            chefImage.setEffect(null);
            doctorImage.setEffect(null);
        });

        chefImage.setOnMouseClicked(e -> {
            selectImage("Chef");
            chefImage.setEffect(glow);
            businessmanImage.setEffect(null);
            doctorImage.setEffect(null);
        });

        doctorImage.setOnMouseClicked(e -> {
            selectImage("Doctor");
            doctorImage.setEffect(glow);
            businessmanImage.setEffect(null);
            chefImage.setEffect(null);
        });
    }

    /**
     * Selects the character based on the given role.
     * Stores the role for later use and sets up UI changes related to character selection.
     *
     * @param role The role of the character to be selected.
     */
    private void selectImage(String role) {
        appearance = role;
        // Add your code here to highlight the selected image
    }

    /**
     * Handles the submit button click event.
     * Retrieves user inputs, transitions to the main menu scene, and passes user inputs to the next scene.
     *
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    protected void onSubmitButtonClick() throws IOException {
        // Do something with the user's inputs
        String username = usernameField.getText();

        // Save or use the above values as needed

        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Apply CSS
        scene.getStylesheets().add(getClass().getResource("mainmenu.css").toExternalForm());

        MainMenuController controller = loader.getController();
        controller.setUsername(username);
        controller.setAppearance(appearance);

        // Get current stage and set the new scene
        Stage currentStage = (Stage) submitButton.getScene().getWindow();
        currentStage.setScene(scene);
    }
}
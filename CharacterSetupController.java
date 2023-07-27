package com.example.oo3demeterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.effect.Glow;

public class CharacterSetupController {

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView businessmanImage, chefImage, doctorImage;

    @FXML
    private Button submitButton;

    private String appearance;

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

    private void selectImage(String role) {
        appearance = role;
        // Add your code here to highlight the selected image
    }

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

    private void closeCurrentWindow() {
        Stage currentStage = (Stage) submitButton.getScene().getWindow();
        currentStage.close();
    }
}
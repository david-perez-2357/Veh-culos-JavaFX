package com.example.vehiculosjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddVehicleController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
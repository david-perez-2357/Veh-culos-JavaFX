package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.models.Vehicle;
import com.example.vehiculosjavafx.GlobalVariables.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import static com.example.vehiculosjavafx.GlobalVariables.*;

public class AddVehicleApp extends Application {


    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddVehicleApp.class.getResource("add_vehicle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 650);
        stage.setTitle("Alquilar vehículo");
        stage.setScene(scene);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        
        stage.show();

        AddVehicleController controller = fxmlLoader.getController();

        getVehicles();
        getCustomers();
        getRents();
        getActiveRents();

        controller.initialize();
    }

    public static void main(String[] args) {
        launch();
    }

}
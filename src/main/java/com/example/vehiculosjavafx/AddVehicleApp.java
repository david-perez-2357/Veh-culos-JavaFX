package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.models.Customer;
import com.example.vehiculosjavafx.models.Vehicle;
import com.example.vehiculosjavafx.models.VehicleRent;
import com.example.vehiculosjavafx.utils.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddVehicleApp extends Application {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<VehicleRent> rents = new ArrayList<>();
    public static final Database db;

    static {
        try {
            db = new Database("jdbc:mysql://localhost:3306/rentavehiculos", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddVehicleApp.class.getResource("add_vehicle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        AddVehicleController controller = fxmlLoader.getController();

        getVehicles();
        System.out.println(vehicles);
    }

    public static void main(String[] args) {
        launch();
    }

    public void getVehicles() throws SQLException {
        ResultSet result = db.executeQuery("SELECT vh.*, IF(sv.fecha_alquiler <= CURRENT_DATE() AND sv.fecha_entrega > CURRENT_DATE(), 1, 0) AS esta_alquilado " +
                "FROM vehiculos vh " +
                "LEFT JOIN servicios sv ON vh.matricula = sv.matricula_vehiculo " +
                "GROUP BY vh.matricula;"
        );

        while (result.next()) {
            vehicles.add(new Vehicle(
                    result.getString("descripcion"),
                    result.getString("marca"),
                    result.getString("matricula"),
                    result.getInt("kilometros"),
                    result.getInt("precio"),
                    result.getBoolean("esta_alquilado")
            ));
        }

        result.close();
    }
}
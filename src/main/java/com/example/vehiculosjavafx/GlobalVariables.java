package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.models.Customer;
import com.example.vehiculosjavafx.models.Vehicle;
import com.example.vehiculosjavafx.models.VehicleRent;
import com.example.vehiculosjavafx.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalVariables {
    public static final List<Vehicle> vehicles = new ArrayList<>();
    public static final List<Customer> customers = new ArrayList<>();
    public static final List<VehicleRent> rents = new ArrayList<>();
    public static final List<VehicleRent> activeRents = new ArrayList<>();
    public static final Database db;

    static {
        try {
            db = new Database("jdbc:mysql://localhost:3306/rentavehiculos", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get vehicles from the database and add them to the vehicles list
     * @throws SQLException
     */
    public static void getVehicles() throws SQLException {
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

    /**
     * Get customers from the database and add them to the customers list
     * @throws SQLException
     */
    public static void getCustomers() throws SQLException {
        ResultSet result = db.executeQuery("SELECT * FROM clientes;");

        while (result.next()) {
            customers.add(new Customer(
                    result.getString("NyA"),
                    result.getString("nif"),
                    result.getString("Direcion"),
                    result.getString("Poblacion")
            ));
        }

        result.close();
    }

    /**
     * Get rents from the database and add them to the rents list
     * @throws SQLException
     */
    public static void getRents() throws SQLException {
        ResultSet result = db.executeQuery("SELECT * FROM servicios;");

        while (result.next()) {
            Vehicle vehicle = vehicles.stream()
                    .filter(v -> {
                        try {
                            return v.getTuition().equals(result.getString("matricula_vehiculo"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst()
                    .orElse(null);

            Customer customer = customers.stream()
                    .filter(c -> {
                        try {
                            return c.getNif().equals(result.getString("nif_cliente"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst()
                    .orElse(null);

            rents.add(new VehicleRent(
                    vehicle,
                    customer,
                    result.getDate("fecha_alquiler").toLocalDate(),
                    result.getDate("fecha_entrega").toLocalDate(),
                    result.getDouble("Total")
            ));
        }

        result.close();
    }

    /**
     * Get active rents from the rents list
     */
    public static void getActiveRents() {
        activeRents.clear();
        activeRents.addAll(rents.stream()
                .filter(r -> r.getEndDate().isAfter(LocalDate.now()))
                .toList()
        );
    }
}

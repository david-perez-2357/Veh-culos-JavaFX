package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.models.Customer;
import com.example.vehiculosjavafx.models.Vehicle;
import com.example.vehiculosjavafx.models.VehicleRent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

import static com.example.vehiculosjavafx.GlobalVariables.addRent;
import static com.example.vehiculosjavafx.GlobalVariables.db;

public class AddVehicleController {
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox vehicle;
    @FXML
    private ComboBox customer;
    @FXML
    private TextField description;
    @FXML
    private TextField brand;
    @FXML
    private TextField km;
    @FXML
    private TextField price;
    @FXML
    private TextField nif;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField totalPrice;

    public void initialize() {
        vehicle.getItems().addAll(GlobalVariables.vehicles.stream().filter(v -> !v.isRented()).toList());
        customer.getItems().addAll(GlobalVariables.customers);
    }

    @FXML
    private void vehicleChanged() {
        Vehicle selectedVehicle = (Vehicle) vehicle.getValue();
        if (selectedVehicle != null) {
            description.setText(selectedVehicle.getDescription());
            brand.setText(selectedVehicle.getBrand());
            km.setText(selectedVehicle.getKmDone().toString());
            price.setText(selectedVehicle.getPricePerDay().toString());
            calculateTotalPrice();
        }
    }

    @FXML
    private void customerChanged() {
        Customer selectedCustomer = (Customer) customer.getValue();
        if (selectedCustomer != null) {
            nif.setText(selectedCustomer.getNif());
            city.setText(selectedCustomer.getCity());
            address.setText(selectedCustomer.getAddress());
        }
    }

    private void calculateTotalPrice() {
        Vehicle selectedVehicle = (Vehicle) vehicle.getValue();
        if (selectedVehicle != null && startDate.getValue() != null && endDate.getValue() != null) {
            long days = startDate.getValue().until(endDate.getValue()).getDays();
            totalPrice.setText(String.valueOf(selectedVehicle.getPricePerDay() * days));
        }
    }

    @FXML
    private void dateChanged() {
        calculateTotalPrice();
    }

    @FXML
    private void doRent() throws SQLException {
        Vehicle selectedVehicle = (Vehicle) vehicle.getValue();
        Customer selectedCustomer = (Customer) customer.getValue();

        if (selectedVehicle == null || selectedCustomer == null || startDate.getValue() == null || endDate.getValue() == null || totalPrice.getText().isEmpty()) {
            GlobalVariables.showAlert("Ha dejado campos sin rellenar", Alert.AlertType.ERROR);
            return;
        }

        if (startDate.getValue().isAfter(endDate.getValue())) {
            GlobalVariables.showAlert("La fecha de inicio no puede ser posterior a la fecha de fin", Alert.AlertType.ERROR);
            return;
        }

        VehicleRent newRent = new VehicleRent(
                selectedVehicle,
                selectedCustomer,
                startDate.getValue(),
                endDate.getValue(),
                Double.parseDouble(totalPrice.getText())
        );

        if (GlobalVariables.addRent(newRent)) {
            GlobalVariables.rents.add(newRent);
            selectedVehicle.setRented(true);
            vehicle.getItems().clear();
            vehicle.getItems().addAll(GlobalVariables.vehicles.stream().filter(v -> !v.isRented()).toList());
            GlobalVariables.showAlert("Alquiler añadido correctamente", Alert.AlertType.INFORMATION);
        }
    }
}
package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.models.VehicleRent;
import com.example.vehiculosjavafx.utils.TableViewManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.vehiculosjavafx.GlobalVariables.*;
import static com.example.vehiculosjavafx.GlobalVariables.getActiveRents;

public class RentsViewController {
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox customerSelector;
    @FXML
    private TableView rentTable;
    private TableViewManager<VehicleRent> rentTableManager;
    @FXML
    private Button newRentButton;

    public void initialize() throws SQLException {
        rentTableManager = new TableViewManager<VehicleRent>(rentTable);
        rentTableManager.addColumn("Matrícula", r -> r.getVehicle().getTuition());
        rentTableManager.addColumn("Marca", r -> r.getVehicle().getBrand());
        rentTableManager.addColumn("Fecha alquiler", VehicleRent::getStartDate);
        rentTableManager.addColumn("Fecha entrega", VehicleRent::getEndDate);
        rentTableManager.addColumn("Precio total", VehicleRent::getTotalPrice);

        getVehicles();
        getCustomers();
        getRents();
        getActiveRents();

        rentTableManager.addAllData(rents);

        customerSelector.getItems().addAll(customers);
    }

    private void clearFilters(String exception) {
        if (!exception.equals("startDate")) {
            startDate.setValue(null);
        }

        if (!exception.equals("endDate")) {
            endDate.setValue(null);
        }

        if (!exception.equals("customer")) {
            customerSelector.setValue(null);
        }
    }

    @FXML
    private void startDateChanged() {
        if (startDate.getValue() == null) {
            return;
        }

        clearFilters("startDate");
        rentTableManager.filter(rent -> !rent.getStartDate().isBefore(startDate.getValue()));
    }

    @FXML
    private void endDateChanged() {
        if (endDate.getValue() == null) {
            return;
        }

        clearFilters("endDate");
        rentTableManager.filter(rent -> !rent.getEndDate().isAfter(endDate.getValue()));
    }

    @FXML
    private void customerSelectorChanged() {
        if (customerSelector.getValue() == null) {
            return;
        }

        clearFilters("customer");
        rentTableManager.filter(rent -> rent.getCustomer().equals(customerSelector.getValue()));
    }

    @FXML
    private void newRentClick() throws SQLException, IOException {
        newRentButton.getScene().getWindow().hide();
        new AddVehicleApp().start((Stage) newRentButton.getScene().getWindow());
    }
}

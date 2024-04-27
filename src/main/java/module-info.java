module com.example.vehiculosjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires org.json;

    opens com.example.vehiculosjavafx to javafx.fxml;
    exports com.example.vehiculosjavafx;
    exports com.example.vehiculosjavafx.utils;
    opens com.example.vehiculosjavafx.utils to javafx.fxml;
}
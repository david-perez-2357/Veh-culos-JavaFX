package com.example.vehiculosjavafx;

import com.example.vehiculosjavafx.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database("jdbc:mysql://localhost:3306/persona", "root", "");

        ResultSet result = db.executeQuery("SELECT * FROM persona");

        while (result.next()) {
            System.out.println(result.getString("nombre") + " " + result.getInt("edad"));
        }

        result.close();
    }
}

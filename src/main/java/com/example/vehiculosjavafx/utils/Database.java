package com.example.vehiculosjavafx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    protected Connection connection;
    protected final String url;
    protected final String username;
    protected final String password;

    /**
     * Constructor
     * @param url example: "jdbc:mysql://localhost:3306/vehiculos"
     * @param username example: "root"
     * @param password example: "1234"
     */
    public Database(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            this.connect();
        }catch (SQLException e) {
            throw new SQLException("Error connecting to the database", e);
        }

    }

    /**
     * Connect to the database
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Error loading JDBC driver", e);
        }
    }

    /**
     * Disconnect from the database
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Execute a query that returns a ResultSet
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet executeQuery(String query) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        }catch (SQLException e) {
            throw new SQLException("Error executing query", e);
        }

    }

    /**
     * Execute an update, insert or delete query
     * @param query
     * @return rows affected
     * @throws SQLException
     */
    public int executeUpdate(String query) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException("Error executing query", e);
        }
    }
}


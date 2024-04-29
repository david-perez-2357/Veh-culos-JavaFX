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

        this.connect();
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

    /**
     * Inicia una transacción en la base de datos
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        try {
            connection.setAutoCommit(false); // Desactiva el modo de autocommit para comenzar la transacción
        } catch (SQLException e) {
            throw new SQLException("Error starting transaction", e);
        }
    }

    /**
     * Confirma la transacción en la base de datos
     * @throws SQLException
     */
    public void commitTransaction() throws SQLException {
        try {
            connection.commit(); // Confirma la transacción
            connection.setAutoCommit(true); // Restaura el modo de autocommit
        } catch (SQLException e) {
            throw new SQLException("Error committing transaction", e);
        }
    }

    /**
     * Cancela la transacción en la base de datos
     * @throws SQLException
     */
    public void rollbackTransaction() throws SQLException {
        try {
            connection.rollback(); // Cancela la transacción
            connection.setAutoCommit(true); // Restaura el modo de autocommit
        } catch (SQLException e) {
            throw new SQLException("Error rolling back transaction", e);
        }
    }

    /**
     * Ejecutar una consulta preparada con parámetros
     * @param query La consulta SQL con parámetros de marcador de posición (?)
     * @param values Los valores para los parámetros de la consulta
     * @return El número de filas afectadas por la consulta
     * @throws SQLException Si hay un error al ejecutar la consulta
     */
    public int executePreparedStatement(String query, Object... values) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            // Asignar los valores a los parámetros de la consulta
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            // Ejecutar la consulta y retornar el número de filas afectadas
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error executing prepared statement", e);
        }
    }

}


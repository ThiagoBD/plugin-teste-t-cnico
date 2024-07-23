package br.com.thiago.testeTecnicoPlugin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() {
        try {
            // Substitua com seus próprios detalhes de conexão
            String url = "jdbc:mysql://localhost:3306/minecraft";
            String user = "username";
            String password = "password";
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

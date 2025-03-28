package com.MagaCityCab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/MagaCityCabDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";  // Change if your MySQL user is different
    private static final String PASSWORD = "";  // Change if your MySQL password is set
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("🔍 Checking MySQL JDBC Driver...");
                Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Ensure MySQL JDBC Driver is loaded
                System.out.println("✅ MySQL JDBC Driver Found!");

                System.out.println("🔌 Connecting to database...");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ ERROR: Failed to connect to database. Check credentials and DB server!");
            e.printStackTrace();
        }
        return connection;
    }
}

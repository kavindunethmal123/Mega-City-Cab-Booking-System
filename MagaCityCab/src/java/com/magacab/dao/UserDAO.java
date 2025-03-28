package com.MagaCityCab.dao;

import com.MagaCityCab.model.User;
import com.MagaCityCab.utils.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {

   
    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (name, address, nic, phone, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getNic());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



   
    public static User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("customerId"),  
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("nic"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static List<User> getAllCustomers() {
    List<User> customers = new ArrayList<>();
    String sql = "SELECT * FROM users ORDER BY customerid DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            User user = new User(
                rs.getInt("customerid"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("nic"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("password")
            );
            customers.add(user);

            // Debugging line
            System.out.println("✅ Fetched Customer: " + user.getCustomerId() + " - " + user.getName());
        }

        System.out.println("✅ Total Customers Loaded: " + customers.size());

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return customers;
}
    

    
    public static List<User> getCustomersByNIC(String nic) {
        List<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE nic LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nic + "%"); // Search using wildcard
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customers.add(new User(
                        rs.getInt("customerid"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("nic"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    
    public static boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM users WHERE customerid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
    
}

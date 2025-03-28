package com.MagaCityCab.dao;

import com.MagaCityCab.model.Driver;
import com.MagaCityCab.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.MagaCityCab.model.Ride;

public class DriverDAO {

   
 public static List<Driver> getAllDrivers() {
    List<Driver> drivers = new ArrayList<>();
    String sql = "SELECT * FROM drivers";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
Driver driver = new Driver(
    rs.getInt("driver_id"),      // ✅ Correct syntax for getInt()
    rs.getString("name"),        // ✅ Correct syntax for getString()
    rs.getString("nic"),         // ✅ Correct syntax for getString()
    rs.getString("phone"),       // ✅ Correct syntax for getString()
    rs.getString("license_number"), // ✅ Correct syntax for getString()
    rs.getInt("vehicle_id")      // ✅ Correct syntax for getInt()
);
            drivers.add(driver);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("🚨 Error fetching drivers: " + e.getMessage());
    }

    return drivers;
}

    
    public static boolean addDriver(String name, String nic, String phone, String licenseNumber, int vehicleId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO drivers (name, nic, phone, license_number, vehicle_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, nic);
            stmt.setString(3, phone);
            stmt.setString(4, licenseNumber);
            stmt.setInt(5, vehicleId);

            int rowsInserted = stmt.executeUpdate();
            conn.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
public static boolean updateDriver(int driverId, String name, String nic, String phone, String licenseNumber, int vehicleId) {
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE drivers SET name = ?, nic = ?, phone = ?, license_number = ?, vehicle_id = ? WHERE driver_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, nic);
        stmt.setString(3, phone);
        stmt.setString(4, licenseNumber);
        stmt.setInt(5, vehicleId);
        stmt.setInt(6, driverId);

        int rowsUpdated = stmt.executeUpdate();
        conn.close();
        return rowsUpdated > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
    public static boolean deleteDriver(int driverId) {
        String sql = "DELETE FROM drivers WHERE driver_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
     
    public static List<Driver> searchDrivers(String searchQuery) {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM drivers WHERE nic LIKE ? OR name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchQuery + "%");
            stmt.setString(2, "%" + searchQuery + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(new Driver(
                    rs.getInt("driver_id"),
                    rs.getString("name"),
                    rs.getString("nic"),
                    rs.getString("phone"),
                    rs.getString("license_number"),
                    rs.getInt("vehicle_id")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drivers;
    }
    


public static Driver getDriverByNameAndNIC(String name, String nic) {
    Driver driver = null;
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM drivers WHERE name = ? AND nic = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name.trim());
        stmt.setString(2, nic.trim());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            driver = new Driver(
                rs.getInt("driver_id"),  // Ensure column name is correct
                rs.getString("name"),
                rs.getString("nic"),
                rs.getString("phone"),
                rs.getString("license_number"),
                rs.getInt("vehicle_id")
            );
            System.out.println("✅ Driver Found: " + driver.getName());
        } else {
            System.out.println("❌ Driver Not Found: " + name + " | " + nic);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return driver;
}

 
 
    public static List<Ride> getAssignedRides(int driverId) {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE driver_id = ? ORDER BY booking_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ride ride = new Ride(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("booking_number"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getInt("distance"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getBigDecimal("amount"),
                    rs.getString("status"),
                    rs.getString("payment_status")
                );
                rides.add(ride);
            }

            // Debugging Log
            System.out.println("✅ Assigned Rides Found for Driver ID: " + driverId);
            System.out.println("📌 Total Bookings Found: " + rides.size());

        } catch (SQLException e) {
            System.err.println("🚨 Error fetching assigned rides for driver: " + driverId);
            e.printStackTrace();
        }
        return rides;
    }
 
 
}
      

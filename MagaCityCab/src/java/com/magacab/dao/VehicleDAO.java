package com.MagaCityCab.dao;

import com.MagaCityCab.model.Vehicle;
import com.MagaCityCab.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;  // ✅ Import BigDecimal

public class VehicleDAO {

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("model"),
                    rs.getString("plate_number"),
                    rs.getInt("capacity"),
                    rs.getBigDecimal("price_per_km")  // ✅ Correct retrieval
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static BigDecimal getPricePerKm(int vehicleId) {
        String sql = "SELECT price_per_km FROM vehicles WHERE vehicle_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("price_per_km");  // ✅ Ensure BigDecimal is returned
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;  
    }
    
   

    
    public static List<Vehicle> searchVehicles(String plateNumber) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE plate_number LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + plateNumber + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("model"),
                    rs.getString("plate_number"),
                    rs.getInt("capacity"),
                    rs.getBigDecimal("price_per_km")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    
public static boolean addVehicle(Vehicle vehicle) {
    String sql = "INSERT INTO vehicles (model, plate_number, capacity, price_per_km) VALUES (?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, vehicle.getModel());
        stmt.setString(2, vehicle.getPlateNumber());
        stmt.setInt(3, vehicle.getCapacity());
        stmt.setBigDecimal(4, vehicle.getPricePerKm());

        int rowsInserted = stmt.executeUpdate();

        
        System.out.println("🔹 SQL Query Executed: " + stmt.toString());
        System.out.println("🔹 Rows Inserted: " + rowsInserted);

        return rowsInserted > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    
    public static boolean updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET model = ?, plate_number = ?, capacity = ?, price_per_km = ? WHERE vehicle_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getModel());
            stmt.setString(2, vehicle.getPlateNumber());
            stmt.setInt(3, vehicle.getCapacity());
            stmt.setBigDecimal(4, vehicle.getPricePerKm());
            stmt.setInt(5, vehicle.getVehicleId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
    
    
    
    
    

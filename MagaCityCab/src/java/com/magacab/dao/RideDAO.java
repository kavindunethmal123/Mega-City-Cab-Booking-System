package com.MagaCityCab.dao;

import com.MagaCityCab.model.Ride;
import com.MagaCityCab.utils.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class RideDAO {

    
    public static boolean bookRide(Ride ride) {
        String sql = "INSERT INTO bookings (customer_id, booking_number, pickup_location, destination, distance, vehicle_id, driver_id, amount, status, payment_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ride.getCustomerId());
            stmt.setInt(2, ride.getBookingNumber());
            stmt.setString(3, ride.getPickupLocation());
            stmt.setString(4, ride.getDestination());
            stmt.setInt(5, ride.getDistance());
            stmt.setInt(6, ride.getVehicleId());
            stmt.setInt(7, ride.getDriverId()); // Default driver is 0 (not assigned)
            stmt.setBigDecimal(8, ride.getAmount());
            stmt.setString(9, ride.getStatus());
            stmt.setString(10, ride.getPaymentStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static List<Ride> getUserBookings(int customerId) {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ? ORDER BY booking_number DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rides.add(mapResultSetToRide(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rides;
    }

    
    public static List<Ride> getAllBookings() {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM bookings ORDER BY booking_number DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rides.add(mapResultSetToRide(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rides;
    }

   
    public static List<Ride> getBookingsByStatus(String status) {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = ? ORDER BY booking_number DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rides.add(mapResultSetToRide(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rides;
    }

public static boolean updateBooking(int bookingId, int driverId, int vehicleId, String status) {
        String sql = "UPDATE bookings SET driver_id = ?, vehicle_id = ?, status = ? WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            stmt.setInt(2, vehicleId);
            stmt.setString(3, status);
            stmt.setInt(4, bookingId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



   
    public static List<Ride> getUnpaidBookings(int customerId) {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ? AND payment_status = 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rides.add(mapResultSetToRide(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rides;
    }


private static Ride mapResultSetToRide(ResultSet rs) throws SQLException {
    return new Ride(
        rs.getInt("booking_id"),        // ✅ Correctly fetching booking_id
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
}

public static boolean updateDriverForBooking(int bookingId, int driverId) {
    String sql = "UPDATE bookings SET driver_id = ? WHERE booking_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Set values
        stmt.setInt(1, driverId);
        stmt.setInt(2, bookingId);

        // Debugging Logs
        System.out.println("🔹 Executing Query: " + sql);
        System.out.println("🔹 Parameters: DriverID = " + driverId + ", BookingID = " + bookingId);

        // Execute update
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("✅ Driver Assigned Successfully!");
            return true;
        } else {
            System.out.println("❌ No rows updated. Booking ID might be incorrect.");
            return false;
        }

    } catch (SQLException e) {
        System.err.println("🚨 SQL Exception: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    
    
    public static boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);

            // 🔍 Debugging Logs
            System.out.println("🔹 Executing Query: " + sql);
            System.out.println("🔹 Parameters: Status=" + status + ", Booking ID=" + bookingId);

            // Execute update
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Booking Updated in Database!");
                return true;
            } else {
                System.out.println("❌ No rows updated. Booking ID might be incorrect.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("🚨 SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean assignDriver(int bookingNumber, int driverId) {
    String sql = "UPDATE bookings SET driver_id = ? WHERE booking_number = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, driverId);
        stmt.setInt(2, bookingNumber);

        return stmt.executeUpdate() > 0; 

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    
    public static boolean updateBooking(int bookingNumber, String status) {
    String sql = "UPDATE bookings SET status = ? WHERE booking_number = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, status);
        stmt.setInt(2, bookingNumber);

        int rowsUpdated = stmt.executeUpdate();

        System.out.println("📌 SQL Executed: " + sql);
        System.out.println("📌 Rows Affected: " + rowsUpdated);

        return rowsUpdated > 0; 

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("❌ SQL Exception: " + e.getMessage());
    }
    return false;
}
    
    
    public static List<Ride> getBookingsByStatusAndUser(String status, int customerId) {
    List<Ride> rides = new ArrayList<>();
    String sql = "SELECT * FROM bookings WHERE status = ? AND customer_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, status);
        stmt.setInt(2, customerId);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rides.add(mapResultSetToRide(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rides;
}
      
    
    
public static boolean updatePaymentStatus(int bookingId, String paymentStatus) {
    String sql = "UPDATE bookings SET payment_status = ? WHERE booking_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, paymentStatus);
        stmt.setInt(2, bookingId);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


public static Ride getBookingById(int bookingId) {
        Ride ride = null;
        String sql = "SELECT b.*, c.name AS customer_name FROM bookings b " +
                     "JOIN customers c ON b.customer_id = c.customer_id WHERE b.booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ride = new Ride(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ride;
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

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rides;
}




}

    
    
    

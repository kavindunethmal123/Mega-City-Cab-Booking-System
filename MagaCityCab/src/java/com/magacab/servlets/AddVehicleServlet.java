package com.MagaCityCab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.MagaCityCab.utils.DBConnection;

@WebServlet(name = "AddVehicleServlet", urlPatterns = {"/AddVehicleServlet"})
public class AddVehicleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Fix encoding issue

        // ✅ Retrieve form data
        String model = request.getParameter("model");
        String plateNumber = request.getParameter("plateNumber");
        String capacityStr = request.getParameter("capacity");
        String pricePerKmStr = request.getParameter("pricePerKm");

        // ✅ Debugging: Print received values
        System.out.println("🔹 Received Data in AddVehicleServlet:");
        System.out.println("Model: " + model);
        System.out.println("Plate Number: " + plateNumber);
        System.out.println("Capacity: " + capacityStr);
        System.out.println("Price per KM: " + pricePerKmStr);

        // ✅ Validate input fields
        if (model == null || plateNumber == null || capacityStr == null || pricePerKmStr == null ||
            model.trim().isEmpty() || plateNumber.trim().isEmpty() || capacityStr.trim().isEmpty() || pricePerKmStr.trim().isEmpty()) {
            response.getWriter().write("❌ Error: Missing Fields");
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityStr);
            double pricePerKm = Double.parseDouble(pricePerKmStr);

            // ✅ Insert data into the database
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO vehicles (model, plate_number, capacity, price_per_km) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, model);
            stmt.setString(2, plateNumber);
            stmt.setInt(3, capacity);
            stmt.setDouble(4, pricePerKm);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Vehicle Added Successfully!");
                response.getWriter().write("success");
            } else {
                System.out.println("❌ Failed to Add Vehicle!");
                response.getWriter().write("error");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("❌ Database Error: " + e.getMessage());
        }
    }
}
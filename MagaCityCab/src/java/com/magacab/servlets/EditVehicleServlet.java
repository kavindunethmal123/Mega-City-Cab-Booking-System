package com.MagaCityCab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.MagaCityCab.utils.DBConnection;

@WebServlet("/EditVehicleServlet")
public class EditVehicleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String vehicleIdStr = request.getParameter("vehicleId");
        String model = request.getParameter("model");
        String plateNumber = request.getParameter("plateNumber");
        String capacityStr = request.getParameter("capacity");
        String pricePerKmStr = request.getParameter("pricePerKm");

        System.out.println("🔹 Received Edit Data:");
        System.out.println("Vehicle ID: " + vehicleIdStr);
        System.out.println("Model: " + model);
        System.out.println("Plate Number: " + plateNumber);
        System.out.println("Capacity: " + capacityStr);
        System.out.println("Price per KM: " + pricePerKmStr);

        if (vehicleIdStr == null || model == null || plateNumber == null || capacityStr == null || pricePerKmStr == null ||
            vehicleIdStr.trim().isEmpty() || model.trim().isEmpty() || plateNumber.trim().isEmpty() || 
            capacityStr.trim().isEmpty() || pricePerKmStr.trim().isEmpty()) {
            response.getWriter().write("❌ Error: Missing Fields");
            return;
        }

        try {
            int vehicleId = Integer.parseInt(vehicleIdStr);
            int capacity = Integer.parseInt(capacityStr);
            double pricePerKm = Double.parseDouble(pricePerKmStr);

            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE vehicles SET model = ?, plate_number = ?, capacity = ?, price_per_km = ? WHERE vehicle_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, model);
            stmt.setString(2, plateNumber);
            stmt.setInt(3, capacity);
            stmt.setDouble(4, pricePerKm);
            stmt.setInt(5, vehicleId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Vehicle Updated Successfully!");
                response.getWriter().write("success");
            } else {
                System.out.println("❌ Failed to Update Vehicle!");
                response.getWriter().write("error");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("❌ Error: " + e.getMessage());
        }
    }
}
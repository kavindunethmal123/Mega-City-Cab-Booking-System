package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.DriverDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditDriverServlet")
public class EditDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String name = request.getParameter("name");
            String nic = request.getParameter("nic");
            String phone = request.getParameter("phone");
            String licenseNumber = request.getParameter("licenseNumber");
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

            // ✅ Debugging: Print received data
            System.out.println("🔹 Editing Driver: " + driverId + ", " + name + ", " + nic + ", " + phone + ", " + licenseNumber + ", " + vehicleId);

            boolean success = DriverDAO.updateDriver(driverId, name, nic, phone, licenseNumber, vehicleId);

            if (success) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }
}
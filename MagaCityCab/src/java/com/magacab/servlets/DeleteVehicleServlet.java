package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.VehicleDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteVehicleServlet")
public class DeleteVehicleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleIdStr = request.getParameter("vehicleId");

        if (vehicleIdStr != null && !vehicleIdStr.isEmpty()) {
            try {
                int vehicleId = Integer.parseInt(vehicleIdStr);
                boolean deleted = VehicleDAO.deleteVehicle(vehicleId);

                if (deleted) {
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("error");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().write("invalid_id");
            }
        } else {
            response.getWriter().write("missing_id");
        }
    }
}
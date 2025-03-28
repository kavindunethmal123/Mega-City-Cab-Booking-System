package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.RideDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AssignDriverServlet")
public class AssignDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingNumber = Integer.parseInt(request.getParameter("bookingNumber"));
            int driverId = Integer.parseInt(request.getParameter("driverId"));

            boolean success = RideDAO.assignDriver(bookingNumber, driverId);

            if (success) {
                response.sendRedirect("manageBookings.jsp?success=true");
            } else {
                response.sendRedirect("manageBookings.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageBookings.jsp?error=true");
        }
    }
}
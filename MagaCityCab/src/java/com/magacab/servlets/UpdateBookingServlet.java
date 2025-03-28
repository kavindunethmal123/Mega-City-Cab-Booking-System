package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.RideDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateBookingServlet")
public class UpdateBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from request
            int bookingNumber = Integer.parseInt(request.getParameter("bookingNumber"));
            String status = request.getParameter("status");

            System.out.println("🔍 Received Update Request: Booking Number = " + bookingNumber + ", Status = " + status);

            
            boolean success = RideDAO.updateBooking(bookingNumber, status);

            if (success) {
                System.out.println("✅ Booking Updated Successfully!");
                response.sendRedirect("manageBookings.jsp?updateSuccess=true");
            } else {
                System.out.println("❌ Booking Update Failed.");
                response.sendRedirect("manageBookings.jsp?updateError=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("🚨 Exception in UpdateBookingServlet: " + e.getMessage());
            response.sendRedirect("manageBookings.jsp?updateError=true");
        }
    }
}
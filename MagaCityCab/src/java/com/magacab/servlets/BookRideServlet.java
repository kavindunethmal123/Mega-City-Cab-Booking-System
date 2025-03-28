package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.RideDAO;
import com.MagaCityCab.model.Ride;
import com.MagaCityCab.model.User;
import com.MagaCityCab.observer.CustomerObserver;
import com.MagaCityCab.observer.RideBookingManager;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookRideServlet")
public class BookRideServlet extends HttpServlet {
    private static final RideBookingManager rideBookingManager = new RideBookingManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("=== RECEIVED BOOKING DATA ===");
            System.out.println("Booking Number: " + request.getParameter("bookingNumber"));
            System.out.println("Pickup Location: " + request.getParameter("pickup"));
            System.out.println("Destination: " + request.getParameter("destination"));
            System.out.println("Distance: " + request.getParameter("distance"));
            System.out.println("Vehicle ID: " + request.getParameter("vehicle_id"));

            // Validate User Session
            HttpSession userSession = request.getSession(false);
            if (userSession == null || userSession.getAttribute("user") == null) {
                System.out.println("🚨 Error: User session not found.");
                response.sendRedirect("newBooking.jsp?error=true");
                return;
            }

            User user = (User) userSession.getAttribute("user");
            int customerId = user.getCustomerId(); 

            // Retrieve booking details
            int bookingNumber = Integer.parseInt(request.getParameter("bookingNumber"));
            String pickup = request.getParameter("pickup");
            String destination = request.getParameter("destination");
            int distance = Integer.parseInt(request.getParameter("distance"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));

            // Calculate total cost
            BigDecimal totalAmount = new BigDecimal(distance * 50);  // Assuming a fixed rate per KM

            // Create Ride Object
            Ride ride = new Ride(
                0,            
                customerId,   
                bookingNumber,
                pickup,
                destination,
                distance,
                vehicleId,
                0,  
                totalAmount,
                "Pending",
                "Unpaid"
            );

            System.out.println("✅ Created Ride Object: " + ride);

            // Store booking in database
            boolean success = RideDAO.bookRide(ride);

            if (success) {
                System.out.println("✅ Booking Successfully Inserted!");

                // Implement Observer Pattern: Notify the customer
                CustomerObserver customerObserver = new CustomerObserver(user.getName());
                rideBookingManager.addObserver(customerObserver);
                rideBookingManager.bookRide(user.getName());

                // Redirect with success message
                response.sendRedirect("newBooking.jsp?rideSuccess=true");
            } else {
                System.out.println("❌ Booking Insertion Failed.");
                response.sendRedirect("newBooking.jsp?error=true");
            }

        } catch (NumberFormatException e) {
            System.out.println("🚨 Number Format Exception: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("newBooking.jsp?error=invalidNumber");
        } catch (Exception e) {
            System.out.println("🚨 General Exception in BookRideServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("newBooking.jsp?error=true");
        }
    }
}
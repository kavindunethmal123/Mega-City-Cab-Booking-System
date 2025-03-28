<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="com.MagaCityCab.dao.RideDAO" %>
<%@ page import="com.MagaCityCab.model.Ride" %>
<%@ page import="java.util.List" %>

<%
    HttpSession userSession = request.getSession(false);
    User user = (userSession != null) ? (User) userSession.getAttribute("user") : null;

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Ride> rides = RideDAO.getUserBookings(user.getCustomerId());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking History - MagaCityCab</title>

    
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <div>Welcome, <%= user.getName() %>!</div>
        <a href="LogoutServlet">Logout</a>
    </div>

    <!-- Booking History Container -->
    <div class="container">
        <h2>🚖 Your Booking History</h2>

        <table class="history-table">
            <thead>
                <tr>
                    <th>Booking No</th>
                    <th>Pickup</th>
                    <th>Destination</th>
                    <th>Distance (KM)</th>
                    <th>Vehicle Type</th>
                    <th>Total Price</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% if (rides.isEmpty()) { %>
                    <tr>
                        <td colspan="7" class="text-center text-muted">No bookings found.</td>
                    </tr>
                <% } else { %>
                    <% for (Ride ride : rides) { %>
                        <tr>
                            <td><%= ride.getBookingNumber() %></td>
                            <td><%= ride.getPickupLocation() %></td>
                            <td><%= ride.getDestination() %></td>
                            <td><%= ride.getDistance() %></td>
                            <td><%= ride.getVehicleId() %></td>
                            <td>LKR <%= ride.getAmount() %></td>
                            <td>
                                <% if (ride.getStatus().equals("Completed")) { %>
                                    <span class="status-completed">Completed</span>
                                <% } else if (ride.getStatus().equals("Pending")) { %>
                                    <span class="status-pending">Pending</span>
                                <% } else if (ride.getStatus().equals("Cancelled")) { %>
                                    <span class="status-cancelled">Cancelled</span>
                                <% } else { %>
                                    <span class="text-muted"><%= ride.getStatus() %></span>
                                <% } %>
                            </td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
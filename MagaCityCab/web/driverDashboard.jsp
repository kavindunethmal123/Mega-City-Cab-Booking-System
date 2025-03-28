<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.MagaCityCab.model.Ride" %>
<%@ page import="com.MagaCityCab.model.Driver" %>
<%@ page import="com.MagaCityCab.dao.RideDAO" %>

<%
    // Get session and driver details
    HttpSession sessionObj = request.getSession(false);
    Driver driver = (sessionObj != null) ? (Driver) sessionObj.getAttribute("driver") : null;

    // Redirect if not logged in
    if (driver == null) {
        response.sendRedirect("driverLogin.jsp");
        return;
    }

    int driverId = driver.getDriverId();
    List<Ride> assignedRides = RideDAO.getAssignedRides(driverId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver Dashboard - MagaCityCab</title>
    <link rel="stylesheet" type="text/css" href="css/adminStyles.css">
   
</head>
<body>

    <!-- ✅ Navbar -->
    <div class="navbar">
        <div>Welcome, <%= driver.getName() %>!</div>
        <a href="LogoutServlet">Logout</a>
    </div>

    <!-- ✅ Dashboard Container -->
    <div class="container">
        <h2>🚖 Your Assigned Rides</h2>

        <table class="ride-table">
            <thead>
                <tr>
                    <th>Booking No</th>
                    <th>Pickup</th>
                    <th>Destination</th>
                    <th>Distance (KM)</th>
                    <th>Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% if (assignedRides.isEmpty()) { %>
                    <tr>
                        <td colspan="6" class="text-center text-muted">No assigned rides yet.</td>
                    </tr>
                <% } else { %>
                    <% for (Ride ride : assignedRides) { %>
                        <tr>
                            <td><%= ride.getBookingNumber() %></td>
                            <td><%= ride.getPickupLocation() %></td>
                            <td><%= ride.getDestination() %></td>
                            <td><%= ride.getDistance() %> km</td>
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

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
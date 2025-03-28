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

    <!-- ✅ Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #8f94fb, #4e54c8);
            min-height: 100vh;
            padding-top: 60px;
            font-family: "Segoe UI", sans-serif;
        }

        .navbar {
            background-color: #4e54c8;
            color: white;
            padding: 1rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .container {
            background-color: #fff;
            border-radius: 16px;
            padding: 2rem;
            margin-top: 2rem;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
        }

        h2 {
            color: #4e54c8;
            margin-bottom: 1.5rem;
        }

        .status-completed {
            color: green;
            font-weight: bold;
        }

        .status-pending {
            color: orange;
            font-weight: bold;
        }

        .status-cancelled {
            color: red;
            font-weight: bold;
        }

        table {
            margin-top: 1rem;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .text-muted {
            font-style: italic;
        }
    </style>
</head>
<body>

    <!-- ✅ Navbar -->
    <div class="navbar">
        <div>👋 Welcome, <%= user.getName() %>!</div>
        <a href="LogoutServlet" class="btn btn-light">Logout</a>
    </div>

    <!-- ✅ Booking History Section -->
    <div class="container">
        <h2>🚖 Your Booking History</h2>

        <div class="table-responsive">
            <table class="table table-bordered table-striped align-middle">
                <thead class="table-primary text-center">
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
                            <tr class="text-center">
                                <td><%= ride.getBookingNumber() %></td>
                                <td><%= ride.getPickupLocation() %></td>
                                <td><%= ride.getDestination() %></td>
                                <td><%= ride.getDistance() %></td>
                                <td><%= ride.getVehicleId() %></td>
                                <td>LKR <%= ride.getAmount() %></td>
                                <td>
                                    <% if ("Completed".equals(ride.getStatus())) { %>
                                        <span class="status-completed">Completed</span>
                                    <% } else if ("Pending".equals(ride.getStatus())) { %>
                                        <span class="status-pending">Pending</span>
                                    <% } else if ("Cancelled".equals(ride.getStatus())) { %>
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
    </div>

</body>
</html>

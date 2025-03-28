<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession adminSession = request.getSession(false);
    String adminUser = (adminSession != null) ? (String) adminSession.getAttribute("adminUser") : null;

    if (adminUser == null) {
        response.sendRedirect("adminLogin.jsp"); // Redirect if not logged in
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - MagaCityCab</title>
    <link rel="stylesheet" type="text/css" href="css/adminStyles.css">
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-left">
            <h2>🚖 MagaCityCab - Admin Panel</h2>
        </div>
        <div class="navbar-right">
            <span>Welcome, <%= adminUser %>!</span>
            <a href="AdminLogoutServlet" class="logout-btn">Logout</a>
        </div>
    </div>

    <!-- Sidebar + Content -->
    <div class="admin-container">
        
        <!-- Sidebar -->
        <div class="sidebar">
            <ul>
                <li><a href="manageBookings.jsp">Manage Bookings</a></li>
                <li><a href="manageCustomers.jsp">Manage Customers</a></li>
                <li><a href="manageVehicles.jsp">Manage Vehicles</a></li>
                <li><a href="manageDrivers.jsp">‍Manage Drivers</a></li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="content">
            <h2>Welcome to Admin Dashboard</h2>
            <p>Select an option from the sidebar to manage bookings, customers, or vehicles.</p>
        </div>

    </div>

</body>
</html>

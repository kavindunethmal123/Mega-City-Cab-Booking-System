<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession userSession = request.getSession(false);
    User user = (userSession != null) ? (User) userSession.getAttribute("user") : null;

    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard - MagaCityCab</title>

    <!-- ✅ Bootstrap 5 CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #8f94fb, #4e54c8);
            min-height: 100vh;
            padding-top: 60px;
            font-family: "Segoe UI", sans-serif;
            color: #333;
        }

        .navbar {
            background-color: #4e54c8;
            padding: 1rem;
        }

        .dashboard-container {
            background-color: white;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
            max-width: 600px;
            width: 100%;
        }

        .dashboard-container h2 {
            color: #4e54c8;
        }

        .dashboard-container p {
            font-size: 1rem;
            margin-bottom: 0.5rem;
        }

        .btn {
            min-width: 200px;
        }
    </style>
</head>
<body>

    <!-- ✅ Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container d-flex justify-content-between align-items-center">
            <span class="navbar-brand fw-bold">👋 Welcome, <%= user.getName() %>!</span>
            <a href="LogoutServlet" class="btn btn-danger">Logout</a>
        </div>
    </nav>

    <!-- ✅ Dashboard -->
    <div class="container d-flex justify-content-center mt-5">
        <div class="dashboard-container">
            <h2 class="text-center fw-bold mb-4">👤 User Details</h2>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Phone:</strong> <%= user.getPhone() %></p>
            <p><strong>NIC:</strong> <%= user.getNic() %></p>
            <p><strong>Address:</strong> <%= user.getAddress() %></p>

            <hr class="my-4">

            <div class="d-flex flex-column align-items-center gap-3">
                <a href="newBooking.jsp" class="btn btn-success fw-bold">
                    🚖 Book a Ride
                </a>
                <a href="bookingHistory.jsp" class="btn btn-info fw-bold text-white">
                    📜 View Booking History
                </a>
                <a href="payments.jsp" class="btn btn-warning fw-bold">
                    💳 Go to Payment
                </a>
                <a href="help.html" class="btn fw-bold text-white" style="background-color: #6610f2;">
                    ❓ Help
                </a>
            </div>
        </div>
    </div>

    <!-- ✅ Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="com.MagaCityCab.dao.VehicleDAO" %>
<%@ page import="com.MagaCityCab.model.Vehicle" %>
<%@ page import="java.util.List" %>

<%
    HttpSession userSession = request.getSession(false);
    User user = (userSession != null) ? (User) userSession.getAttribute("user") : null;

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Vehicle> vehicles = VehicleDAO.getAllVehicles();
    int bookingNumber = (int) (Math.random() * 900000) + 100000;
    String rideSuccess = request.getParameter("rideSuccess");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Booking - MagaCityCab</title>

    <!-- ✅ Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            min-height: 100vh;
            padding-top: 60px;
            font-family: "Segoe UI", sans-serif;
        }

        .card {
            border-radius: 16px;
        }

        .card-header h4 {
            margin-bottom: 0;
        }

        .total-price-display {
            font-size: 1.2rem;
            padding: 0.5rem 1rem;
            background-color: #eef0ff;
            border-radius: 8px;
            display: inline-block;
        }

        .alert {
            font-size: 0.95rem;
        }
    </style>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- ✅ JS for Price Calculation -->
    <script>
        function calculatePrice() {
            let distance = document.getElementById("distance").value;
            let vehicleDropdown = document.getElementById("vehicle_id");
            let selectedOption = vehicleDropdown.options[vehicleDropdown.selectedIndex];
            let pricePerKm = selectedOption.getAttribute("data-price");

            if (distance && pricePerKm) {
                let totalPrice = parseFloat(distance) * parseFloat(pricePerKm);
                document.getElementById("totalPrice").innerText = "LKR " + totalPrice.toFixed(2);
                document.getElementById("totalAmount").value = totalPrice.toFixed(2);
            } else {
                document.getElementById("totalPrice").innerText = "LKR 0.00";
                document.getElementById("totalAmount").value = "0.00";
            }
        }
    </script>
</head>
<body>

    <!-- ✅ Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary p-3 fixed-top">
        <div class="container d-flex justify-content-between align-items-center">
            <span class="navbar-brand">👋 Welcome, <%= user.getName() %>!</span>
            <a href="LogoutServlet" class="btn btn-light">Logout</a>
        </div>
    </nav>

    <!-- ✅ Booking Form -->
    <div class="container mt-5 pt-5">
        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-6">

                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white text-center">
                        <h4>🚖 Book a Ride</h4>
                    </div>
                    <div class="card-body">

                        <% if ("true".equals(rideSuccess)) { %>
                            <div class="alert alert-success text-center">🎉 Booking Successful!</div>
                        <% } %>

                        <% if ("true".equals(error)) { %>
                            <div class="alert alert-danger text-center">⚠️ Booking Failed. Try Again.</div>
                        <% } %>

                        <form action="BookRideServlet" method="post">
                            <div class="mb-3">
                                <label class="form-label">Booking Number</label>
                                <input type="text" name="bookingNumber" class="form-control" value="<%= bookingNumber %>" readonly>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Customer Name</label>
                                <input type="text" name="name" class="form-control" value="<%= user.getName() %>" readonly>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Address</label>
                                <input type="text" name="address" class="form-control" value="<%= user.getAddress() %>" readonly>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Phone</label>
                                <input type="text" name="phone" class="form-control" value="<%= user.getPhone() %>" readonly>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Pickup Location</label>
                                <input type="text" name="pickup" class="form-control" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Destination</label>
                                <input type="text" name="destination" class="form-control" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Distance (KM)</label>
                                <input type="number" id="distance" name="distance" class="form-control" required oninput="calculatePrice()">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Select Vehicle Type</label>
                                <select id="vehicle_id" name="vehicle_id" class="form-select" required onchange="calculatePrice()">
                                    <% for (Vehicle vehicle : vehicles) { %>
                                        <option value="<%= vehicle.getVehicleId() %>" data-price="<%= vehicle.getPricePerKm() %>">
                                            <%= vehicle.getModel() %> - LKR <%= vehicle.getPricePerKm() %>/KM
                                        </option>
                                    <% } %>
                                </select>
                            </div>

                            <!-- Hidden field for price -->
                            <input type="hidden" id="totalAmount" name="totalAmount" value="0.00">

                            <div class="mb-4 text-center">
                                <h5>Total Price:</h5>
                                <span id="totalPrice" class="total-price-display text-primary fw-bold">LKR 0.00</span>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Submit Booking</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

</body>
</html>

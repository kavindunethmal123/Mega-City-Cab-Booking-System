<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="com.MagaCityCab.dao.VehicleDAO" %>
<%@ page import="com.MagaCityCab.model.Vehicle" %>
<%@ page import="java.util.List" %>

<%
    // Get user session
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
   
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function calculatePrice() {
            let distance = document.getElementById("distance").value;
            let vehicleDropdown = document.getElementById("vehicle_id");
            let selectedOption = vehicleDropdown.options[vehicleDropdown.selectedIndex];
            let pricePerKm = selectedOption.getAttribute("data-price");

            if (distance && pricePerKm) {
                let totalPrice = parseFloat(distance) * parseFloat(pricePerKm);
                document.getElementById("totalPrice").innerText = "LKR " + totalPrice.toFixed(2);
                document.getElementById("totalAmount").value = totalPrice.toFixed(2); // Store total in hidden input
            } else {
                document.getElementById("totalPrice").innerText = "LKR 0.00";
                document.getElementById("totalAmount").value = "0.00";
            }
        }
    </script>
</head>
<body class="bg-light">

    <!-- ✅ Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary p-3">
        <div class="container">
            <span class="navbar-brand">Welcome, <%= user.getName() %>!</span>
            <a href="LogoutServlet" class="btn btn-danger">Logout</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">

                
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

                            <!-- Hidden field for total amount -->
                            <input type="hidden" id="totalAmount" name="totalAmount" value="0.00">

                            <div class="mb-3 text-center">
                                <h5>Total Price: <span id="totalPrice" class="text-primary fw-bold">LKR 0.00</span></h5>
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
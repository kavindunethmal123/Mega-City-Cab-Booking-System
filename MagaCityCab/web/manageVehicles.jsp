<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.MagaCityCab.dao.VehicleDAO" %>
<%@ page import="com.MagaCityCab.model.Vehicle" %>
<%@ page import="java.util.List" %>

<%
    String searchQuery = request.getParameter("searchQuery");
    List<Vehicle> vehicles = (searchQuery == null || searchQuery.isEmpty()) ? 
                             VehicleDAO.getAllVehicles() : 
                             VehicleDAO.searchVehicles(searchQuery);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Vehicles - MagaCityCab</title>
    <link rel="stylesheet" type="text/css" href="css/adminStyles.css">
</head>
<body>

    <div class="navbar">
        <h2>🚗 MagaCityCab - Admin Panel</h2>
        <a href="AdminLogoutServlet" class="logout-btn">Logout</a>
    </div>

    <div class="admin-container">
        <div class="sidebar">
            <ul>
                <li><a href="manageBookings.jsp">📑 Manage Bookings</a></li>
                <li><a href="manageCustomers.jsp">👥 Manage Customers</a></li>
                <li><a href="manageVehicles.jsp">🚗 Manage Vehicles</a></li>
                 <li><a href="manageDrivers.jsp">👨🏻 ‍Manage Drivers</a></li>
            </ul>
        </div>

        <div class="content">
            <h2>🚗 Manage Vehicles</h2>

            <form method="get">
                <input type="text" name="searchQuery" placeholder="Search by Plate Number">
                <button type="submit">Search</button>
                <button type="button" onclick="openModal()">➕ Add Vehicle</button>
            </form>

            <table border="1">
                <tr>
                    <th>Vehicle ID</th>
                    <th>Model</th>
                    <th>Plate Number</th>
                    <th>Capacity</th>
                    <th>Price per KM</th>
                    <th>Action</th>
                </tr>
                <% for (Vehicle vehicle : vehicles) { %>
                    <tr>
                        <td><%= vehicle.getVehicleId() %></td>
                        <td><%= vehicle.getModel() %></td>
                        <td><%= vehicle.getPlateNumber() %></td>
                        <td><%= vehicle.getCapacity() %></td>
                                            <td>LKR <%= vehicle.getPricePerKm() %></td>
                                            <td>
                        <a href="#" onclick="openEditModal('<%= vehicle.getVehicleId() %>', 
                                                           '<%= vehicle.getModel() %>', 
                                                           '<%= vehicle.getPlateNumber() %>', 
                                                           '<%= vehicle.getCapacity() %>', 
                                                           '<%= vehicle.getPricePerKm() %>')">Edit</a> |
                        <a href="DeleteVehicleServlet?vehicleId=<%= vehicle.getVehicleId() %>">Delete</a>
                    </td>
                    </tr>
                <% } %>
            </table>
        </div>
    </div>

   
    <div id="addVehiclePopup" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add Vehicle</h2>
            <form id="addVehicleForm">
                <label>Model:</label>
                <input type="text" name="model" id="model" required>

                <label>Plate Number:</label>
                <input type="text" name="plateNumber" id="plateNumber" required>

                <label>Capacity:</label>
                <input type="number" name="capacity" id="capacity" required>

                <label>Price per KM:</label>
                <input type="number" step="0.01" name="pricePerKm" id="pricePerKm" required>

                <button type="button" onclick="submitVehicle()">Add Vehicle</button>
            </form>
        </div>
    </div>

    <script>
        function openModal() {
            document.getElementById("addVehiclePopup").style.display = "block";
        }

        function closeModal() {
            document.getElementById("addVehiclePopup").style.display = "none";
        }

            function submitVehicle() {
                var formData = new FormData(document.getElementById("addVehicleForm"));

                fetch('AddVehicleServlet', {
                    method: 'POST',
                    body: new URLSearchParams(formData) 
                })
                .then(response => response.text())
                .then(data => {
                    console.log("🔹 Server Response:", data);
                    if (data.trim() === "success") {
                        alert("✅ Vehicle Added Successfully!");
                        closeModal();
                        location.reload();
                    } else {
                        alert("❌ Error Adding Vehicle! Check Console.");
                    }
                })
                .catch(error => console.error("❌ Error:", error));
            }
            
            
            
            
function openEditModal(id, model, plateNumber, capacity, pricePerKm) {
    document.getElementById("editVehicleId").value = id;
    document.getElementById("editModel").value = model;
    document.getElementById("editPlateNumber").value = plateNumber;
    document.getElementById("editCapacity").value = capacity;
    document.getElementById("editPricePerKm").value = pricePerKm;
    
    document.getElementById("editVehiclePopup").style.display = "block";
}

// Close Edit Modal
function closeEditModal() {
    document.getElementById("editVehiclePopup").style.display = "none";
}

// Submit Edited Vehicle Data
function submitEditVehicle() {
    var formData = new FormData(document.getElementById("editVehicleForm"));

    fetch('EditVehicleServlet', {
        method: 'POST',
        body: new URLSearchParams(formData) 
    })
    .then(response => response.text())
    .then(data => {
        console.log("🔹 Server Response:", data);
        if (data.trim() === "success") {
            alert("✅ Vehicle Updated Successfully!");
            closeEditModal();
            location.reload();
        } else {
            alert("❌ Error Updating Vehicle! Check Console.");
        }
    })
    .catch(error => console.error("❌ Error:", error));
}  
            
            
            
            
    </script>

    
    <!-- Edit Vehicle Modal -->
<div id="editVehiclePopup" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditModal()">&times;</span>
        <h2>Edit Vehicle</h2>
        <form id="editVehicleForm">
            <input type="hidden" name="vehicleId" id="editVehicleId">

            <label>Model:</label>
            <input type="text" name="model" id="editModel" required>

            <label>Plate Number:</label>
            <input type="text" name="plateNumber" id="editPlateNumber" required>

            <label>Capacity:</label>
            <input type="number" name="capacity" id="editCapacity" required>

            <label>Price per KM:</label>
            <input type="number" step="0.01" name="pricePerKm" id="editPricePerKm" required>

            <button type="button" onclick="submitEditVehicle()">Update Vehicle</button>
        </form>
    </div>
</div>
    
    
 
    
    
    
    
</body>
</html>
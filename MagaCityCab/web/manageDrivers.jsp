<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.MagaCityCab.dao.DriverDAO" %>
<%@ page import="com.MagaCityCab.model.Driver" %>
<%@ page import="java.util.List" %>

<%
    String searchQuery = request.getParameter("searchQuery");
    List<Driver> drivers = (searchQuery == null || searchQuery.isEmpty()) ? 
                           DriverDAO.getAllDrivers() : 
                           DriverDAO.searchDrivers(searchQuery);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Drivers - MagaCityCab</title>
    <link rel="stylesheet" type="text/css" href="css/adminStyles.css">
    
</head>
<body>

    <div class="navbar">
        <h2>🚖 MagaCityCab - Admin Panel</h2>
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
            <h2>🚖 Manage Drivers</h2>

            <form method="get">
                <input type="text" name="searchQuery" placeholder="Search by NIC or Name">
                <button type="submit">Search</button>
                <button type="button" onclick="openAddModal()">➕ Add Driver</button>
            </form>

            <table border="1">
                <tr>
                    <th>Driver ID</th>
                    <th>Name</th>
                    <th>NIC</th>
                    <th>Phone</th>
                    <th>License Number</th>
                    <th>Vehicle ID</th>
                    <th>Action</th>
                </tr>
                <% for (Driver driver : drivers) { %>
                    <tr>
                        <td><%= driver.getDriverId() %></td>
                        <td><%= driver.getName() %></td>
                        <td><%= driver.getNic() %></td>
                        <td><%= driver.getPhone() %></td>
                        <td><%= driver.getLicenseNumber() %></td>
                        <td><%= driver.getVehicleId() %></td>
                        <td>
                            <a href="#" onclick="openEditModal('<%= driver.getDriverId() %>', 
                                                               '<%= driver.getName() %>', 
                                                               '<%= driver.getNic() %>', 
                                                               '<%= driver.getPhone() %>', 
                                                               '<%= driver.getLicenseNumber() %>', 
                                                               '<%= driver.getVehicleId() %>')">Edit</a> |
                            <a href="DeleteDriverServlet?driverId=<%= driver.getDriverId() %>">Delete</a>
                        </td>
                    </tr>
                <% } %>
            </table>
        </div>
    </div>

    
    <div id="addDriverPopup" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add Driver</h2>
            <form id="addDriverForm">
                <label>Name:</label>
                <input type="text" name="name" id="name" required>

                <label>NIC:</label>
                <input type="text" name="nic" id="nic" required>

                <label>Phone:</label>
                <input type="text" name="phone" id="phone" required>

                <label>License Number:</label>
                <input type="text" name="licenseNumber" id="licenseNumber" required>

                <label>Vehicle ID:</label>
                <input type="number" name="vehicleId" id="vehicleId" required>

                <button type="button" onclick="submitDriver()">Add Driver</button>
            </form>
        </div>
    </div>

    
    <div id="editDriverPopup" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Edit Driver</h2>
            <form id="editDriverForm">
                <input type="hidden" name="driverId" id="editDriverId">
                
                <label>Name:</label>
                <input type="text" name="name" id="editName" required>

                <label>NIC:</label>
                <input type="text" name="nic" id="editNic" required>

                <label>Phone:</label>
                <input type="text" name="phone" id="editPhone" required>

                <label>License Number:</label>
                <input type="text" name="licenseNumber" id="editLicenseNumber" required>

                <label>Vehicle ID:</label>
                <input type="number" name="vehicleId" id="editVehicleId" required>

                <button type="button" onclick="updateDriver()">Update Driver</button>
            </form>
        </div>
    </div>

    <script>
        function openAddModal() {
            document.getElementById("addDriverPopup").style.display = "block";
        }

        function openEditModal(driverId, name, nic, phone, licenseNumber, vehicleId) {
            document.getElementById("editDriverId").value = driverId;
            document.getElementById("editName").value = name;
            document.getElementById("editNic").value = nic;
            document.getElementById("editPhone").value = phone;
            document.getElementById("editLicenseNumber").value = licenseNumber;
            document.getElementById("editVehicleId").value = vehicleId;

            document.getElementById("editDriverPopup").style.display = "block";
        }

        function closeModal() {
            document.getElementById("addDriverPopup").style.display = "none";
            document.getElementById("editDriverPopup").style.display = "none";
        }

        function submitDriver() {
            var formData = new FormData(document.getElementById("addDriverForm"));

            fetch('AddDriverServlet', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
            .then(response => response.text())
            .then(data => {
                console.log("🔹 Server Response:", data);
                if (data.trim() === "success") {
                    alert("✅ Driver Added Successfully!");
                    closeModal();
                    location.reload();
                } else {
                    alert("❌ Error Adding Driver! Check Console.");
                }
            })
            .catch(error => console.error("❌ Error:", error));
        }

        function updateDriver() {
            var formData = new FormData(document.getElementById("editDriverForm"));

            fetch('EditDriverServlet', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "success") {
                    alert("✅ Driver Updated Successfully!");
                    closeModal();
                    location.reload();
                } else {
                    alert("❌ Error Updating Driver! Check Console.");
                }
            })
            .catch(error => console.error("❌ Error:", error));
        }
    </script>

</body>
</html>
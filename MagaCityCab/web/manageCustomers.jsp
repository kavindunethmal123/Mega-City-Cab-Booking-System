<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.MagaCityCab.dao.UserDAO" %>
<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="java.util.List" %>

<%
    // Get the search parameter from the request
    String searchNIC = request.getParameter("nic");
    List<User> customers;

    if (searchNIC != null && !searchNIC.isEmpty()) {
        customers = UserDAO.getCustomersByNIC(searchNIC); // Fetch customer by NIC
    } else {
        customers = UserDAO.getAllCustomers(); // Fetch all customers
    }
    //out.println("Debug: Total Customers Fetched: " + customers.size()); // Debugging line
%>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Customers - MagaCityCab</title>
    <link rel="stylesheet" type="text/css" href="css/adminStyles.css">
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-left">
            <h2>🚖 MagaCityCab - Admin Panel</h2>
        </div>
        <div class="navbar-right">
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
            <h2>👥 Manage Customers</h2>

            <!-- Search Bar -->
            <form method="GET" action="manageCustomers.jsp">
                <label for="nicSearch">Search by NIC:</label>
                <input type="text" name="nic" id="nicSearch" placeholder="Enter NIC" value="<%= searchNIC != null ? searchNIC : "" %>">
                <button type="submit">Search</button>
                <a href="manageCustomers.jsp"><button type="button">Reset</button></a>
            </form>

            <br>

            <!-- Customer Table -->
            <table border="1">
                <tr>
                    <th>Customer ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>NIC</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>

                <% if (customers.isEmpty()) { %>
                    <tr>
                        <td colspan="7">No customers found.</td>
                    </tr>
                <% } else { %>
                    <% for (User customer : customers) { %>
                        <tr>
                            <td><%= customer.getCustomerId() %></td>
                            <td><%= customer.getName() %></td>
                            <td><%= customer.getAddress() %></td>
                            <td><%= customer.getNic() %></td>
                            <td><%= customer.getPhone() %></td>
                            <td><%= customer.getEmail() %></td>
                            <td>
                                <form action="DeleteCustomerServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this customer?');">
                                    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
                                    <button type="submit" class="delete-btn">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                <% } %>
            </table>

        </div>

    </div>

</body>
</html>

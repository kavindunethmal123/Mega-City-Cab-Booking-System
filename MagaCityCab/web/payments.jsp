<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.MagaCityCab.dao.RideDAO" %>
<%@ page import="com.MagaCityCab.model.Ride" %>
<%@ page import="com.MagaCityCab.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession userSession = request.getSession(false);
    User user = (userSession != null) ? (User) userSession.getAttribute("user") : null;

    if (user == null) {
        response.sendRedirect("login.jsp"); 
        return;
    }

    List<Ride> bookings = RideDAO.getBookingsByStatusAndUser("Completed", user.getCustomerId());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment - MagaCityCab</title>

    <!-- ✅ Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #8f94fb, #4e54c8);
            font-family: "Segoe UI", sans-serif;
            padding-top: 60px;
        }

        .container {
            background-color: white;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
        }

        table {
            margin-top: 1rem;
        }

        .btn-apply {
            background-color: #4e54c8;
            color: white;
            border: none;
            padding: 0.3rem 0.7rem;
            border-radius: 6px;
        }

        .btn-apply:hover {
            background-color: #3b40a4;
        }

        .btn-pay {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 0.4rem 0.8rem;
            margin-bottom: 0.3rem;
        }

        .btn-pay:hover {
            background-color: #218838;
        }

        .btn-info {
            background-color: #17a2b8;
            color: white;
            padding: 0.4rem 0.8rem;
        }

        .btn-info:hover {
            background-color: #138496;
        }

        h2 {
            color: #4e54c8;
        }

        td span {
            font-size: 0.85rem;
        }
    </style>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function applyCoupon(bookingId) {
            let couponCode = document.getElementById("coupon_" + bookingId).value;
            let totalAmount = parseFloat(document.getElementById("amount_" + bookingId).innerText.replace("LKR ", ""));
            let discount = 0;

            if (couponCode === "DISCOUNT10") {
                discount = totalAmount * 0.10;
            } else if (couponCode === "DISCOUNT20") {
                discount = totalAmount * 0.20;
            }

            let tax = (totalAmount - discount) * 0.12;
            let finalAmount = totalAmount - discount + tax;

            document.getElementById("discount_" + bookingId).innerText = "Discount: LKR " + discount.toFixed(2);
            document.getElementById("tax_" + bookingId).innerText = "Tax (12%): LKR " + tax.toFixed(2);
            document.getElementById("final_" + bookingId).innerText = "Final Payable Amount: LKR " + finalAmount.toFixed(2);
        }

        function makePayment(bookingId) {
            if (!bookingId || bookingId.trim() === "") {
                alert("Error: Booking ID is missing!");
                return;
            }

            fetch("PaymentServlet", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: "bookingId=" + encodeURIComponent(bookingId)
            })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "success") {
                    alert("✅ Payment Successful!");
                    location.reload();
                } else {
                    alert("❌ Payment Failed. Try Again!");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("❌ Error processing payment.");
            });
        }

        function downloadInvoice(bookingId) {
            window.open("InvoiceServlet?bookingId=" + bookingId, "_blank");
        }
    </script>
</head>
<body>

    <!-- ✅ Page Container -->
    <div class="container my-5">
        <h2 class="text-center mb-4">💳 Payment Page</h2>

        <% if (bookings.isEmpty()) { %>
            <p class="text-muted text-center">No completed bookings available for payment.</p>
        <% } else { %>
            <div class="table-responsive">
                <table class="table table-bordered align-middle text-center">
                    <thead class="table-primary">
                        <tr>
                            <th>Booking ID</th>
                            <th>Booking No</th>
                            <th>Pickup</th>
                            <th>Destination</th>
                            <th>Total Price</th>
                            <th>Payment Status</th>
                            <th>Coupon</th>
                            <th>Final Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Ride ride : bookings) { %>
                            <tr>
                                <td><%= ride.getBookingId() %></td>
                                <td><%= ride.getBookingNumber() %></td>
                                <td><%= ride.getPickupLocation() %></td>
                                <td><%= ride.getDestination() %></td>
                                <td id="amount_<%= ride.getBookingId() %>">LKR <%= ride.getAmount() %></td>
                                <td><%= ride.getPaymentStatus() %></td>
                                <td>
                                    <input type="text" class="form-control mb-2" id="coupon_<%= ride.getBookingId() %>" placeholder="Enter Coupon">
                                    <button class="btn-apply" onclick="applyCoupon('<%= ride.getBookingId() %>')">Apply</button>
                                </td>
                                <td>
                                    <span id="discount_<%= ride.getBookingId() %>">Discount: LKR 0.00</span><br>
                                    <span id="tax_<%= ride.getBookingId() %>">Tax (12%): LKR 0.00</span><br>
                                    <span id="final_<%= ride.getBookingId() %>">Final Payable Amount: LKR <%= ride.getAmount() %></span>
                                </td>
                                <td>
                                    <% if (!ride.getPaymentStatus().equals("Paid")) { %>
                                        <button class="btn-pay" onclick="makePayment('<%= ride.getBookingId() %>')">Pay Now</button><br>
                                    <% } %>
                                    <button class="btn btn-info" onclick="downloadInvoice('<%= ride.getBookingId() %>')">Invoice</button>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } %>
    </div>

</body>
</html>

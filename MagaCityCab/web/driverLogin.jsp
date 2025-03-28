<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver Login - MagaCityCab</title>

   
</head>
<body>

    <div class="auth-container">
        <h2>🚖 Driver Login</h2>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                ❌ Invalid Name or NIC. Please try again.
            </div>
        <% } %>

        <form action="DriverLoginServlet" method="post">
            <div class="mb-3 text-start">
                <label for="name" class="form-label fw-bold">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>

            <div class="mb-3 text-start">
                <label for="nic" class="form-label fw-bold">NIC:</label>
                <input type="password" class="form-control" id="nic" name="nic" required>
            </div>

            <button type="submit" class="btn btn-danger w-100">Login</button>
        </form>
    </div>

    <!-- Bootstrap 5 JS (Optional, for better interactivity) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
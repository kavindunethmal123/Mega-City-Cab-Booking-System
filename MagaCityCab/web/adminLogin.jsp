<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login - MagaCityCab</title>

    <!-- ✅ Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: "Segoe UI", sans-serif;
        }

        .login-container {
            max-width: 420px;
            width: 100%;
            background-color: white;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
        }

        .login-container h2 {
            color: #4e54c8;
            margin-bottom: 1.5rem;
        }

        .btn-primary {
            background-color: #4e54c8;
            border: none;
        }

        .btn-primary:hover {
            background-color: #3d41b4;
        }

        .alert {
            font-size: 0.95rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2 class="text-center">🔐 Admin Login</h2>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger text-center">
                ❌ Invalid Username or Password. Please try again.
            </div>
        <% } %>

        <form action="AdminLoginServlet" method="post">
            <div class="mb-3">
                <label for="username" class="form-label fw-bold">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label fw-bold">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>
    </div>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

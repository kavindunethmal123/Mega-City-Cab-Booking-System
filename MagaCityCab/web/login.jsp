<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login - MagaCityCab</title>

    <!-- ✅ Bootstrap 5 CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #4e54c8, #8f94fb);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: "Segoe UI", sans-serif;
        }

        .auth-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }

        .auth-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #4e54c8;
        }

        .alert-danger {
            font-size: 0.95rem;
            margin-bottom: 1rem;
        }

        .btn-primary {
            background-color: #4e54c8;
            border: none;
        }

        .btn-primary:hover {
            background-color: #3d41b4;
        }

        a.text-primary:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="auth-container">
        <h2>🔑 Login to MagaCityCab</h2>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger text-center" role="alert">
                ❌ Invalid Email or Password. Please try again.
            </div>
        <% } %>

        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label for="email" class="form-label fw-bold">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label fw-bold">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <p class="mt-3 text-center">
            Don't have an account?
            <a href="register.jsp" class="fw-bold text-primary">Register here</a>
        </p>
    </div>

    <!-- Optional Bootstrap JS (for interactivity like alerts, modals, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

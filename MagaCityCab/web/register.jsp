<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - MagaCityCab</title>
    <style>
        body {
            margin: 0;
            font-family: "Segoe UI", sans-serif;
            background: linear-gradient(135deg, #4e54c8, #8f94fb);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .auth-container {
            background-color: white;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 450px;
        }

        .auth-container h2 {
            text-align: center;
            margin-bottom: 1rem;
            color: #4e54c8;
        }

        form label {
            display: block;
            margin-top: 1rem;
            margin-bottom: 0.3rem;
            color: #333;
        }

        form input {
            width: 100%;
            padding: 0.6rem;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 1rem;
        }

        button[type="submit"] {
            width: 100%;
            padding: 0.8rem;
            margin-top: 1.5rem;
            background-color: #4e54c8;
            color: white;
            font-size: 1rem;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #3d41b4;
        }

        p {
            text-align: center;
            margin-top: 1rem;
        }

        p a {
            color: #4e54c8;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }

        .success-message {
            background-color: #d4edda;
            color: #155724;
            padding: 0.8rem;
            border-radius: 8px;
            margin-bottom: 1rem;
        }

        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 0.8rem;
            border-radius: 8px;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

    <div class="auth-container">
        <h2>📝 Register for MagaCityCab</h2>

        <% if (request.getAttribute("successMessage") != null) { %>
            <p class="success-message"><%= request.getAttribute("successMessage") %></p>
        <% } %>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>

        <form action="RegisterServlet" method="post">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <label for="nic">NIC:</label>
            <input type="text" id="nic" name="nic" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Register</button>
        </form>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>

</body>
</html>

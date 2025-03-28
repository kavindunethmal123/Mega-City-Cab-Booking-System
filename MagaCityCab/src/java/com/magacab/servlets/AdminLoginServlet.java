package com.MagaCityCab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final String ADMIN_USERNAME = "admin";  // Hardcoded Admin Username
    private static final String ADMIN_PASSWORD = "admin123";  // Hardcoded Admin Password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", username); // Storing Admin Session
            response.sendRedirect("adminDashboard.jsp"); // Redirect to Admin Dashboard
        } else {
            response.sendRedirect("adminLogin.jsp?error=Invalid Credentials");
        }
    }
}

package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.UserDAO;
import com.MagaCityCab.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String password = request.getParameter("password");

        
        if (name == null || email == null || phone == null || address == null || nic == null || password == null ||
            name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || nic.isEmpty() || password.isEmpty()) {
            
            request.setAttribute("errorMessage", "⚠️ All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        
        User newUser = new User(0, name, address, nic, phone, email, password);

        
        boolean success = UserDAO.registerUser(newUser);

        if (success) {
            request.setAttribute("successMessage", "✅ Registration successful! You can now log in.");
        } else {
            request.setAttribute("errorMessage", "❌ Registration failed. Please try again.");
        }

        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
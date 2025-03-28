package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.UserDAO;
import com.MagaCityCab.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = UserDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("userDashboard.jsp");  // ✅ Redirect to dashboard
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}

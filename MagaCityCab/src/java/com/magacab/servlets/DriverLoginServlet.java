package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.DriverDAO;
import com.MagaCityCab.model.Driver;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DriverLoginServlet")
public class DriverLoginServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String name = request.getParameter("name").trim();
    String nic = request.getParameter("nic").trim();

    System.out.println("🔍 Driver Login Attempt: Name=" + name + ", NIC=" + nic);

    Driver driver = DriverDAO.getDriverByNameAndNIC(name, nic);

    if (driver != null) {
        System.out.println("✅ Login Success: " + driver.getName());
        request.getSession().setAttribute("driver", driver);
        response.sendRedirect("driverDashboard.jsp");
    } else {
        System.out.println("❌ Invalid Credentials for Driver: " + name);
        response.sendRedirect("driverLogin.jsp?error=invalid");
    }
}
}
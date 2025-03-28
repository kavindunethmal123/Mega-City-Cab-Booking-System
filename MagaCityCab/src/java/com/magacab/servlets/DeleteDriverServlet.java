package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.DriverDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteDriverServlet")
public class DeleteDriverServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        if (DriverDAO.deleteDriver(driverId)) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }
}
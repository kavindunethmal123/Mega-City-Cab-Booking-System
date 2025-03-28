package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        boolean success = UserDAO.deleteCustomer(customerId);

        if (success) {
            response.sendRedirect("manageCustomers.jsp?deleteSuccess=true");
        } else {
            response.sendRedirect("manageCustomers.jsp?deleteError=true");
        }
    }
}

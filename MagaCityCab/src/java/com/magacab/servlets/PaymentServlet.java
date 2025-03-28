package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.RideDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");

        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            response.getWriter().write("error");
            return;
        }

        try {
            int bookingId = Integer.parseInt(bookingIdStr);
            boolean success = RideDAO.updatePaymentStatus(bookingId, "Paid");

            if (success) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("error");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }
}
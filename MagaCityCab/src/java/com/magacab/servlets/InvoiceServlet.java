package com.MagaCityCab.servlets;

import com.MagaCityCab.dao.RideDAO;
import com.MagaCityCab.model.Ride;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdParam = request.getParameter("bookingId");

        if (bookingIdParam == null || bookingIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Booking ID");
            return;
        }

        int bookingId;
        try {
            bookingId = Integer.parseInt(bookingIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Booking ID Format");
            return;
        }

        Ride ride = RideDAO.getBookingById(bookingId);
        if (ride == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Invoice_" + ride.getBookingNumber() + ".pdf");

        try {
            Document document = new Document();
            ServletOutputStream outStream = response.getOutputStream();
            PdfWriter.getInstance(document, outStream);

            document.open();

            // ✅ Adding Header
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("MagaCityCab Invoice", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n")); // Line Break

            // ✅ Customer Details
            document.add(new Paragraph("Customer Name: " + ride.getCustomerName()));
            document.add(new Paragraph("Pickup Location: " + ride.getPickupLocation()));
            document.add(new Paragraph("Destination: " + ride.getDestination()));
            document.add(new Paragraph("Distance: " + ride.getDistance() + " KM"));
            document.add(new Paragraph("Vehicle Type: " + ride.getVehicleId()));
            document.add(new Paragraph("Status: " + ride.getStatus()));
            document.add(new Paragraph("\n")); // Line Break

            // ✅ Payment Details
            Font amountFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("Total Amount: LKR " + ride.getAmount(), amountFont));

            document.close();
            outStream.flush();
            outStream.close();
        } catch (DocumentException e) {
            throw new IOException("Error while creating PDF", e);
        }
    }
}
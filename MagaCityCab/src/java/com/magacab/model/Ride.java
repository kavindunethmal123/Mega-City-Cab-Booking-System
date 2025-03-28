package com.MagaCityCab.model;

import java.math.BigDecimal;


public class Ride {
    private int bookingId;       // Primary Key
    private int customerId;       // Foreign Key to User table
    private int bookingNumber;    // Unique Booking Number
    private String pickupLocation;
    private String destination;
    private int distance;         // Distance in KM
    private int vehicleId;        // Vehicle Type ID
    private int driverId;         // Assigned Driver ID
    private BigDecimal amount;    // Total Fare Amount
    private String status;        // Booking Status (Pending, Completed, Cancelled)
    private String paymentStatus; // Payment Status (Paid, Unpaid, Pending)
    private String customerName; 
    
 
    public Ride() {
    }

    /** 
     * ✅ Parameterized Constructor
     * @param bookingId Unique booking ID
     * @param customerId Customer associated with booking
     * @param bookingNumber Unique booking number
     * @param pickupLocation Pickup address
     * @param destination Destination address
     * @param distance Distance in KM
     * @param vehicleId Associated vehicle type
     * @param driverId Assigned driver ID
     * @param amount Total fare amount
     * @param status Booking status
     * @param paymentStatus Payment status (Paid/Unpaid)
     */
    public Ride(int bookingId, int customerId, int bookingNumber, String pickupLocation,
                String destination, int distance, int vehicleId, int driverId,
                BigDecimal amount, String status, String paymentStatus) {

        this.bookingId = bookingId;
        this.customerId = customerId;
        this.bookingNumber = bookingNumber;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distance = distance;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.amount = amount;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

  
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getBookingNumber() { return bookingNumber; }
    public void setBookingNumber(int bookingNumber) { this.bookingNumber = bookingNumber; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }


 
    @Override
    public String toString() {
        return "Ride{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", bookingNumber=" + bookingNumber +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", vehicleId=" + vehicleId +
                ", driverId=" + driverId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
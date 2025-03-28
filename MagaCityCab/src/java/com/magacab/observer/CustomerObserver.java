package com.MagaCityCab.observer;

// Implementing Observer Pattern for Ride Notifications
public class CustomerObserver implements RideObserver {
    private String customerName;

    public CustomerObserver(String customerName) { // Ensure it accepts only 1 argument
        this.customerName = customerName;
    }

    @Override
    public void update(String message) {
        System.out.println("📩 Notification sent to Customer (" + customerName + "): " + message);
    }
}
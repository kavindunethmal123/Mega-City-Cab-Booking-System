package com.MagaCityCab.observer;

import java.util.ArrayList;
import java.util.List;

// Observable (Subject)
public class RideBookingManager {
    private List<RideObserver> observers = new ArrayList<>();

    // Register an observer (Customer)
    public void addObserver(RideObserver observer) {
        observers.add(observer);
    }

    // Notify the observer (Customer)
    public void notifyObservers(String message) {
        for (RideObserver observer : observers) {
            observer.update(message);
        }
    }

    // Method to trigger ride booking notification
    public void bookRide(String customerName) {
        String message = "Your ride has been successfully booked!";
        notifyObservers(message);
    }
}
package com.MagaCityCab.model;

import java.math.BigDecimal;

public class Vehicle {
    private int vehicleId;
    private String model;
    private String plateNumber;
    private int capacity;
    private BigDecimal pricePerKm;

    // ✅ Constructor
    public Vehicle(int vehicleId, String model, String plateNumber, int capacity, BigDecimal pricePerKm) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.plateNumber = plateNumber;
        this.capacity = capacity;
        this.pricePerKm = pricePerKm;
    }

    // ✅ Getters and Setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public BigDecimal getPricePerKm() { return pricePerKm; }
    public void setPricePerKm(BigDecimal pricePerKm) { this.pricePerKm = pricePerKm; }
}

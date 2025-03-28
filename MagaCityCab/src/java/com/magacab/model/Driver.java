package com.MagaCityCab.model;

public class Driver {
    private int driverId;
    private String name;
    private String nic;
    private String phone;
    private String licenseNumber;
    private int vehicleId;

    // Default Constructor
    public Driver() {}

    // Constructor with driverId (Used when retrieving an existing driver from the database)
    public Driver(int driverId, String name, String nic, String phone, String licenseNumber, int vehicleId) {
        this.driverId = driverId;
        this.name = name;
        this.nic = nic;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.vehicleId = vehicleId;
    }

    // Constructor without driverId (Used when creating a new driver)
    public Driver(String name, String nic, String phone, String licenseNumber, int vehicleId) {
        this.name = name;
        this.nic = nic;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.vehicleId = vehicleId;
    }

    // Getters and Setters
    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { 
        if (name != null && !name.trim().isEmpty()) {
            this.name = name; 
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getNic() { return nic; }
    public void setNic(String nic) { 
        if (nic != null && !nic.trim().isEmpty()) {
            this.nic = nic; 
        } else {
            throw new IllegalArgumentException("NIC cannot be empty");
        }
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { 
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone; 
        } else {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
    }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { 
        if (licenseNumber != null && !licenseNumber.trim().isEmpty()) {
            this.licenseNumber = licenseNumber; 
        } else {
            throw new IllegalArgumentException("License Number cannot be empty");
        }
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    // Overriding toString() for easy debugging
    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", phone='" + phone + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
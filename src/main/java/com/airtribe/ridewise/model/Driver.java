package com.airtribe.ridewise.model;

public class Driver {
    private String id;
    private String name;
    private String currentLocation;
    private boolean available;
    private int activeRideCount;
    private VehicleType vehicleType;

    public Driver(VehicleType vehicleType, int activeRideCount, boolean available, String currentLocation, String name, String id) {
        this.vehicleType = vehicleType;
        this.activeRideCount = activeRideCount;
        this.available = available;
        this.currentLocation = currentLocation;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getActiveRideCount() {
        return activeRideCount;
    }

    public void setActiveRideCount(int activeRideCount) {
        this.activeRideCount = activeRideCount;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

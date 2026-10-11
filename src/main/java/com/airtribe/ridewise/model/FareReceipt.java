package com.airtribe.ridewise.model;

import java.time.LocalDateTime;

public class FareReceipt {
   // FareReceipt
    private String rideId;
    private double amount;
    private LocalDateTime generatedAt;

    public FareReceipt(String rideId, double amount, LocalDateTime  generatedAt) {
        this.rideId = rideId;
        this.amount = amount;
        this.generatedAt = generatedAt;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime  getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime  generatedAt) {
        this.generatedAt = generatedAt;
    }
}

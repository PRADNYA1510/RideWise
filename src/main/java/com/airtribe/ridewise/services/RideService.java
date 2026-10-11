package com.airtribe.ridewise.services;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.RideStatus;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class RideService {
    private List<Ride> rides;
    private RideMatchingStrategy matchingStrategy;
    private FareStrategy fareStrategy;
    private DriverService driverService;
    private List<FareReceipt> fareReceipts;

    public RideService(List<Ride> rides, FareStrategy fareStrategy, RideMatchingStrategy matchingStrategy,DriverService driverService,List<FareReceipt> fairReceipt) {
        this.rides = rides;
        this.fareStrategy = fareStrategy;
        this.matchingStrategy = matchingStrategy;
        this.driverService =   driverService;
        this.fareReceipts = fairReceipt;

    }

    public void requestRide(Ride ride){
        ride.setStatus(RideStatus.REQUESTED);
        List<Driver> drivers = driverService.getAvailableDrivers();
        Driver driver = matchingStrategy.findDriver(ride.getRider(),drivers);
        if(driver!=null){
            ride.setDriver(driver);
            driver.setAvailable(false);
            driver.setActiveRideCount(driver.getActiveRideCount()+1);
            ride.setStatus(RideStatus.ASSIGNED);
            double fare =  fareStrategy.calculateFare(ride);
            createFareReceipt(fare,ride.getId(), LocalDateTime.now());
        }
        rides.add(ride);
    }

    public void createFareReceipt(double fare,String rideId,LocalDateTime generatedAt){
        FareReceipt fareReceipt = new FareReceipt(rideId,fare,generatedAt);
        fareReceipts.add(fareReceipt);
    }

    public boolean completeRide(Ride ride){
        if(RideStatus.ASSIGNED.equals(ride.getStatus())){
            ride.setStatus(RideStatus.COMPLETED);
            ride.getDriver().setAvailable(true);
            ride.getDriver().setActiveRideCount(ride.getDriver().getActiveRideCount() - 1);
            return true;
        }
        return false;
    }

    public Ride getRideById(String rideId) {
        for (Ride ride : rides) {
            if (ride.getId().equals(rideId)) {
                return ride;
            }
        }
        return null;
    }


}

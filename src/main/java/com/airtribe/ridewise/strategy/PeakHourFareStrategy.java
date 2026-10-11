package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.VehicleType;

public class PeakHourFareStrategy implements FareStrategy {
    DefaultFareStrategy defaultFare = new DefaultFareStrategy();
    public double getVehicleFareAtPeakHour(VehicleType vehicleType){
        if(vehicleType==VehicleType.BIKE){
            return defaultFare.getVehicleFare(vehicleType) * 1.5;
        }
        else if(vehicleType == VehicleType.AUTO){
            return defaultFare.getVehicleFare(vehicleType) * 2;
        }
        return defaultFare.getVehicleFare(vehicleType)  * 2.5;
    }
    @Override
    public double calculateFare(Ride ride) {
        return ride.getDistance() * getVehicleFareAtPeakHour(ride.getDriver().getVehicleType());
    }
}

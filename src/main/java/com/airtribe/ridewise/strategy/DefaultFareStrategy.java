package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.VehicleType;

public class DefaultFareStrategy implements FareStrategy {

    public int getVehicleFare(VehicleType vehicleType){
        if(vehicleType==VehicleType.BIKE){
           return 10;
        }
        else if(vehicleType == VehicleType.AUTO){
            return 20;
        }
        return 30;
    }
    @Override
    public double calculateFare(Ride ride) {
       return ride.getDistance() * getVehicleFare(ride.getDriver().getVehicleType());
    }
}

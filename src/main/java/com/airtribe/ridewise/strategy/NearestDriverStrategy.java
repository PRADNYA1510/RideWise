package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy{
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        for(Driver driver : drivers){
            if (driver.isAvailable()
                    && rider.getLocation().equals(driver.getCurrentLocation())) {
                return driver;
            }
        }
        return null;
    }
}

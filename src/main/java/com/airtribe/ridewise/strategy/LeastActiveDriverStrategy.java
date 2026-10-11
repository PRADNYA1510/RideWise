package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.List;

public class LeastActiveDriverStrategy  implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        Driver d = null;
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                if (d == null || driver.getActiveRideCount() < d.getActiveRideCount()) {
                    d = driver;
                }
            }
        }
        return d;
    }
}
package com.airtribe.ridewise.services;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.ArrayList;
import java.util.List;

public class DriverService {
    private List<Driver> drivers;

    public DriverService(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Driver getDriverById(String driverId){
        for(Driver driver : drivers){
            if(driver.getId().equals(driverId)){
                return driver;
            }
        }
        return null;
    }

    public void registerDriver(Driver driver){
        if(getDriverById(driver.getId())==null){
            drivers.add(driver);
        }
    }

    public List<Driver> getAvailableDrivers(){
        List<Driver> availableDrivers = new ArrayList<>();
        for(Driver driver : drivers){
            if(driver.isAvailable()){
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public void updateAvailability(String driverId,boolean available){
        Driver driver = getDriverById(driverId);
        if(driver!=null){
            driver.setAvailable(available);
        }
    }


}

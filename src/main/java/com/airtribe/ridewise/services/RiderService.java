package com.airtribe.ridewise.services;

import com.airtribe.ridewise.model.Rider;

import java.util.List;

public class RiderService {
    private List<Rider> riders;

    public RiderService(List<Rider> riders) {
        this.riders = riders;
    }

    public Rider getRiderById(String riderId) {
        for (Rider ride : riders) {
            if (ride.getId().equals(riderId)) {
                return ride;
            }
        }
        return null;
    }

    public void registerRider(Rider rider) {
        if(getRiderById(rider.getId())==null) {
            riders.add(rider);
        }


    }
}

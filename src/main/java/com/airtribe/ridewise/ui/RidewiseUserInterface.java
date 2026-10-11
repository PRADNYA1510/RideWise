package com.airtribe.ridewise.ui;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.model.VehicleType;
import com.airtribe.ridewise.services.DriverService;
import com.airtribe.ridewise.services.RideService;
import com.airtribe.ridewise.services.RiderService;
import com.airtribe.ridewise.strategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RidewiseUserInterface {

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            // In-memory data
            List<Rider> riders = new ArrayList<>();
            List<Driver> drivers = new ArrayList<>();
            List<Ride> rides = new ArrayList<>();
            List<FareReceipt> fareReceipts = new ArrayList<>();

            // Services
            RiderService riderService = new RiderService(riders);
            DriverService driverService = new DriverService(drivers);

            // Strategies
            RideMatchingStrategy matchingStrategy =
                    new NearestDriverStrategy();

            FareStrategy fareStrategy =
                    new DefaultFareStrategy();
            //Strategies
//            RideMatchingStrategy matchingStrategy =
//                    new LeastActiveDriverStrategy();
//
//            FareStrategy fareStrategy =
//                    new PeakHourFareStrategy();

            // Ride service
            RideService rideService = new RideService(
                    rides,
                    fareStrategy,
                    matchingStrategy,
                    driverService,
                    fareReceipts
            );

            boolean running = true;

            while (running) {

                System.out.println("\n==============================");
                System.out.println("       RIDEWISE SYSTEM");
                System.out.println("==============================");
                System.out.println("1. Register Rider");
                System.out.println("2. Register Driver");
                System.out.println("3. View Available Drivers");
                System.out.println("4. Request Ride");
                System.out.println("5. Complete Ride");
                System.out.println("6. View Rides");
                System.out.println("7. View Fare Receipts");
                System.out.println("8. Exit");
                System.out.println("==============================");

                System.out.print("Enter your choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (choice) {

                    case 1:
                        // Register Rider
                        System.out.print("Enter Rider ID: ");
                        String riderId = scanner.nextLine();

                        System.out.print("Enter Rider Name: ");
                        String riderName = scanner.nextLine();

                        System.out.print("Enter Rider Location: ");
                        String riderLocation = scanner.nextLine();

                        Rider rider = new Rider(
                                riderId,
                                riderName,
                                riderLocation
                        );

                        riderService.registerRider(rider);
                        System.out.println("DEBUG Rider ID: " + rider.getId());
                        System.out.println("DEBUG Found Rider: "
                                + riderService.getRiderById(rider.getId()));

                        System.out.println("Rider registered successfully.");
                        break;

                    case 2:
                        // Register Driver
                        System.out.print("Enter Driver ID: ");
                        String driverId = scanner.nextLine();

                        System.out.print("Enter Driver Name: ");
                        String driverName = scanner.nextLine();

                        System.out.print("Enter Driver Location: ");
                        String driverLocation = scanner.nextLine();

                        System.out.println("Select Vehicle Type:");
                        System.out.println("1. BIKE");
                        System.out.println("2. AUTO");
                        System.out.println("3. CAR");

                        System.out.print("Enter vehicle choice: ");

                        int vehicleChoice;

                        try {
                            vehicleChoice =
                                    Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid vehicle choice.");
                            break;
                        }

                        VehicleType vehicleType;

                        switch (vehicleChoice) {
                            case 1:
                                vehicleType = VehicleType.BIKE;
                                break;

                            case 2:
                                vehicleType = VehicleType.AUTO;
                                break;

                            case 3:
                                vehicleType = VehicleType.CAR;
                                break;

                            default:
                                System.out.println("Invalid vehicle type.");
                                continue;
                        }

                        Driver driver = new Driver(
                                vehicleType,
                                0,
                                true,
                                driverLocation,
                                driverName,
                                driverId
                        );

                        driverService.registerDriver(driver);

                        System.out.println("Driver registered successfully.");
                        break;

                    case 3:
                        // View Available Drivers
                        List<Driver> availableDrivers =
                                driverService.getAvailableDrivers();

                        if (availableDrivers.isEmpty()) {
                            System.out.println("No drivers available.");
                        } else {

                            System.out.println("\nAvailable Drivers:");

                            for (Driver availableDriver : availableDrivers) {

                                System.out.println(
                                        "ID: " + availableDriver.getId()
                                                + ", Name: " + availableDriver.getName()
                                                + ", Location: "
                                                + availableDriver.getCurrentLocation()
                                                + ", Vehicle: "
                                                + availableDriver.getVehicleType()
                                );
                            }
                        }

                        break;

                    case 4:
                        // Request Ride
                        System.out.print("Enter Ride ID: ");
                        String rideId = scanner.nextLine();

                        System.out.print("Enter Rider ID: ");
                        String requestRiderId = scanner.nextLine();

                        Rider requestRider =
                                riderService.getRiderById(requestRiderId);

                        if (requestRider == null) {
                            System.out.println("Rider not found.");
                            break;
                        }

                        System.out.print("Enter distance in km: ");

                        double distance;

                        try {
                            distance =
                                    Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid distance.");
                            break;
                        }

                        Ride ride = new Ride(
                                rideId,
                                requestRider,
                                null,
                                distance,
                                null
                        );

                        rideService.requestRide(ride);

                        if (ride.getDriver() != null) {

                            System.out.println(
                                    "Ride assigned successfully."
                            );

                            System.out.println(
                                    "Driver: "
                                            + ride.getDriver().getName()
                            );

                            System.out.println(
                                    "Fare: calculated successfully."
                            );

                        } else {

                            System.out.println(
                                    "No driver available."
                            );
                        }

                        break;

                    case 5:
                        // Complete Ride
                        System.out.print("Enter Ride ID: ");
                        String completeRideId = scanner.nextLine();

                        Ride rideToComplete =
                                rideService.getRideById(completeRideId);

                        if (rideToComplete == null) {
                            System.out.println("Ride not found.");
                            break;
                        }

                        boolean rideStatus = rideService.completeRide(rideToComplete);
                        if(rideStatus) {
                            System.out.println("Ride completed successfully.");
                        }
                        else{
                            System.out.println("Ride could not be completed. Ride must be assigned.");
                        }
                        break;

                    case 6:
                        // View Rides
                        if (rides.isEmpty()) {
                            System.out.println("No rides available.");
                        } else {

                            System.out.println("\nRides:");

                            for (Ride r : rides) {

                                System.out.println(
                                        "Ride ID: " + r.getId()
                                                + ", Rider: "
                                                + r.getRider().getName()
                                                + ", Driver: "
                                                + (r.getDriver() != null
                                                ? r.getDriver().getName()
                                                : "Not Assigned")
                                                + ", Distance: "
                                                + r.getDistance()
                                                + " km"
                                                + ", Status: "
                                                + r.getStatus()
                                );
                            }
                        }

                        break;

                    case 7:
                        // View Fare Receipts
                        if (fareReceipts.isEmpty()) {
                            System.out.println(
                                    "No fare receipts available."
                            );
                        } else {

                            System.out.println("\nFare Receipts:");

                            for (FareReceipt receipt : fareReceipts) {

                                System.out.println(
                                        "Ride ID: "
                                                + receipt.getRideId()
                                                + ", Amount: "
                                                + receipt.getAmount()
                                                + ", Generated At: "
                                                + receipt.getGeneratedAt()
                                );
                            }
                        }

                        break;

                    case 8:
                        running = false;
                        System.out.println(
                                "Thank you for using RideWise!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }
            }

            scanner.close();
        }
    }


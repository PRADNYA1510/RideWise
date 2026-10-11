# RideWise — Modular Ride-Sharing System

## 1. Project Overview

RideWise is a console-based ride-sharing application developed using Java. It allows riders to register, drivers to register, rides to be requested and assigned to available drivers, fares to be calculated, and rides to be completed.

The project demonstrates Object-Oriented Programming (OOP), SOLID principles, design patterns, and separation of responsibilities through a modular architecture.

## 2. Features

- Register riders and drivers.
- View available drivers.
- Request rides and match them with available drivers.
- Support multiple driver-matching strategies.
- Calculate fares using different pricing strategies.
- Track ride status throughout its lifecycle.
- Complete rides and release drivers for future requests.
- Generate and view fare receipts.
- Handle invalid menu input.

## 3. Technology Stack

- **Language:** Java
- **Build Tool:** Maven
- **Application Type:** Console-based application
- **Data Storage:** In-memory collections (`ArrayList`)

## 4. Architecture

The application separates domain models, business services, strategies, and user interaction.

```text
com.airtribe.ridewise
├── model
│   ├── Rider
│   ├── Driver
│   ├── Ride
│   ├── FareReceipt
│   ├── RideStatus
│   └── VehicleType
├── strategy
│   ├── RideMatchingStrategy
│   ├── NearestDriverStrategy
│   ├── LeastActiveDriverStrategy
│   ├── FareStrategy
│   ├── DefaultFareStrategy
│   └── PeakHourFareStrategy
├── services
│   ├── RiderService
│   ├── DriverService
│   └── RideService
└── ui
    └── RidewiseUserInterface
```

## 5. Design Patterns and Principles

### Strategy Pattern

The Strategy Pattern allows driver matching and fare calculation algorithms to be changed independently of the main ride workflow.

**Driver matching**
- `NearestDriverStrategy`: Selects an available driver at the rider's location in the current simplified model.
- `LeastActiveDriverStrategy`: Selects an available driver with the lowest active ride count.

**Fare calculation**
- `DefaultFareStrategy`: Calculates fares using vehicle-specific base rates.
- `PeakHourFareStrategy`: Applies vehicle-specific peak-hour multipliers.

### SOLID Principles

- **SRP:** Rider, driver, and ride services have separate responsibilities.
- **OCP:** New matching or fare strategies can be introduced through the existing interfaces.
- **LSP:** Implementations can be substituted for their corresponding strategy interfaces.
- **ISP:** Matching and fare calculation use separate, focused interfaces.
- **DIP:** `RideService` depends on strategy interfaces instead of concrete strategy implementations.

### Other Principles

- **DRY:** Reuse shared behavior instead of duplicating logic.
- **KISS:** Keep the implementation straightforward.
- **YAGNI:** Avoid unnecessary functionality.
- **Law of Demeter:** Keep interactions focused on the objects needed to perform a task.

## 6. Ride Lifecycle

The supported ride statuses are:

- `REQUESTED`
- `ASSIGNED`
- `COMPLETED`
- `CANCELLED`

A ride request begins in `REQUESTED`. If a suitable driver is found, the ride becomes `ASSIGNED`. Once completed, it transitions to `COMPLETED`, and the driver becomes available again.

If no driver is found, the ride remains `REQUESTED`.

## 7. Fare Calculation

The current example rates are:

| Vehicle | Base fare per km | Peak-hour multiplier |
|---|---:|---:|
| BIKE | 10 | 1.5× |
| AUTO | 20 | 2× |
| CAR | 30 | 2.5× |

These are sample rates used by the application and are not real-world pricing.

**Example:** A 10 km AUTO ride costs 200 using the default strategy and 400 using the peak-hour strategy.

## 8. How to Run

### Prerequisites

- Install a compatible JDK.
- Install IntelliJ IDEA or another Java IDE.
- Ensure Maven is available or use the IDE's bundled Maven support.

### Run the application

1. Clone the repository.
2. Open the project in your IDE.
3. Load or refresh the Maven project.
4. Run `RidewiseUserInterface.java`.
5. Use the console menu to register riders and drivers, request rides, completegit
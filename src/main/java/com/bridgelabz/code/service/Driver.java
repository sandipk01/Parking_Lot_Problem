package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;

public class Driver implements IDriving {

    private ParkingLotSystem parkingLotSystem;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public boolean parkAVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkingLotSystem.parkingVehicle(vehicle);
    }

    @Override
    public boolean unParkAVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkingLotSystem.unPark(vehicle);
    }
}

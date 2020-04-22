package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.ParkingLotOwner;

import java.util.Map;

public class Driver implements IDriving {

    private ParkingLotSystem parkingLotSystem;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    //Method for park a vehicle
    @Override
    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (parkingLotSystem.isParkingLotFull()) {
            throw new ParkingLotException("No Space Available :", ParkingLotException.TypeOfException.NO_PARKING_SPACE);
        } else if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            String key = ParkingAttendant.evenlyDistributeLot(parkingLot);
            parkingLot.put(key, vehicle);
            parkingLotSystem.isParkingLotFull();
            parkingLotSystem.notifyObservers();
        }
        return parkingLot;
    }

    //Method for park a unPark vehicle
    @Override
    public Map<String, Vehicle> unParkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (!parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle not found", ParkingLotException.TypeOfException.VEHICLE_NO_FOUND);
        } else {
            String key = parkingLotSystem.searchAVehicle(vehicle);
            parkingLot.put(key, null);
            parkingLotSystem.isParkingLotFull();
            parkingLotSystem.notifyObservers();
        }
        return parkingLot;
    }
}

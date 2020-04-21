package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.ParkingLotOwner;

import java.util.Map;

public class Driver implements IDriving {

    private ParkingLotSystem parkingLotSystem;
    private ParkingLotOwner parkingLotOwner;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
        this.parkingLotOwner = new ParkingLotOwner();
    }

    @Override
    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (parkingLotSystem.isParkingLotFull()) {
            throw new ParkingLotException("No Space Available :", ParkingLotException.TypeOfException.NO_PARKING_SPACE);
        } else if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            String key = parkingLotOwner.getDecideParkingPosition(parkingLot);
            parkingLot.put(key, vehicle);
            parkingLotSystem.isParkingLotFull();
            parkingLotSystem.notifyObservers();
            System.out.println(vehicle.getBrandName() + " -- " + vehicle.getModelName() +
                    " -- " + vehicle.getNumberPlate() + " Parked At : parking slot No : " + key);
        }
        return parkingLot;
    }

    @Override
    public Map<String, Vehicle> unParkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (!parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle not found", ParkingLotException.TypeOfException.VEHICLE_NO_FOUND);
        } else {
            String key = parkingLotSystem.searchAVehicle(parkingLot, vehicle);
            parkingLot.put(key, null);
            parkingLotSystem.isParkingLotFull();
            parkingLotSystem.notifyObservers();
            System.out.println(vehicle.getBrandName() + " -- " + vehicle.getModelName() +
                    " -- " + vehicle.getNumberPlate() + " UN Parked At : parking slot No : " + key);
        }
        return parkingLot;
    }
}

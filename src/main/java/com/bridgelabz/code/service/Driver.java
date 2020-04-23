package com.bridgelabz.code.service;

import com.bridgelabz.code.enums.VehicleType;
import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;
import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.Map;

public class Driver {

    private ParkingLotSystem parkingLotSystem;
    private ParkingAttendant parkingAttendant;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
        parkingAttendant = new ParkingAttendant(parkingLotSystem);
    }

    //Method for park a vehicle
    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot, VehicleType vehicleType) throws ParkingLotException {
        if (parkingLotSystem.isParkingLotFull()) {
            throw new ParkingLotException("No Space Available :", ParkingLotException.TypeOfException.NO_PARKING_SPACE);
        } else if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            String key;
            switch (vehicleType) {
                case SMALL_VEHICLE:
                    key = parkingAttendant.evenlyDistributeLot(parkingLot);
                    parkingLot.put(key, vehicle);
                    break;
                case LARGE_VEHICLE:
                    key = parkingAttendant.getMaxFreeSpaceLot(parkingLot);
                    parkingLot.put(key, vehicle);
                    break;
            }
        }
        return parkingLot;
    }

    //Method for park a unPark vehicle
    public Map<String, Vehicle> unParkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (!parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle not found", ParkingLotException.TypeOfException.VEHICLE_NO_FOUND);
        } else {
            String key = parkingLotSystem.searchAVehicle(vehicle);
            parkingLot.put(key, null);
        }
        return parkingLot;
    }

}

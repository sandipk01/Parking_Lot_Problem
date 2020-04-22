package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;

import java.util.Map;

public class ParkingAttendant extends Driver {

    private static int section = 65;
    private ParkingLotSystem parkingLotSystem;

    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        super(parkingLotSystem);
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (parkingLotSystem.isParkingLotFull()) {
            throw new ParkingLotException("No Space Available :", ParkingLotException.TypeOfException.NO_PARKING_SPACE);
        } else if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            String key = nearestFreeSpace(parkingLot);
            parkingLot.put(key, vehicle);
            parkingLotSystem.isParkingLotFull();
            parkingLotSystem.notifyObservers();
        }
        return parkingLot;
    }

    public static String nearestFreeSpace(Map<String,Vehicle> parkingLot){
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getValue()==null) {
                return entry.getKey();
            }
        }
        return null;
    }

    //Evenly distributed parking lots.
    public static String evenlyDistributeLot(Map<String, Vehicle> parkingLot) {
        int limit = 65 + ParkingLotSystem.getNoOfParkingLots();
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getKey().contains((char) section + " ") && entry.getValue() == null) {
                section++;
                return entry.getKey();
            }
            if (section >= limit) {
                section = 65;
            }
        }
        return null;
    }
}

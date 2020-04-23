package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;

import java.util.Map;

public class ParkingAttendant {

    private ParkingLotSystem parkingLotSystem;
    private int maxSection = 0;

    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException {
        if (parkingLotSystem.isParkingLotFull()) {
            throw new ParkingLotException("No Space Available :", ParkingLotException.TypeOfException.NO_PARKING_SPACE);
        } else if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            String key = nearestFreeSpace(parkingLot);
            parkingLot.put(key, vehicle);
        }
        return parkingLot;
    }

    public String nearestFreeSpace(Map<String, Vehicle> parkingLot) {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return null;
    }

    //Evenly distributed parking lots.
    public String evenlyDistributeLot(Map<String, Vehicle> parkingLot) {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getKey().contains(maxLotSpace(parkingLot)) && entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return null;
    }

    //Get lot which has Maximum number of free space
    public String getMaxFreeSpaceLot(Map<String, Vehicle> parkingLot) {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getKey().contains(maxLotSpace(parkingLot)) && entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return null;
    }

    //Get lot which has Maximum number of free space
    public String maxLotSpace(Map<String, Vehicle> parkingLot) {
        int section = 65, counter = 0, maxCounter = 0, sectionLimit = 65 + ParkingLotSystem.getNoOfParkingLots();
        if (iSLotEmpty((char) section + " ", parkingLot)) {
            maxSection = section;
            section++;
        } else {
            while (section <= sectionLimit) {
                for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
                    if (entry.getKey().contains((char) section + " ") && entry.getValue() == null) {
                        counter++;
                    }
                }
                if (maxCounter < counter) {
                    maxCounter = counter;
                    maxSection = section;
                }
                counter = 0;
                section++;
            }
        }
        return (char) maxSection + " ";
    }

    //Get Lot which is completely empty
    public boolean iSLotEmpty(String lot, Map<String, Vehicle> parkingLot) {
        int counter = 0;
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getKey().contains(lot) && entry.getValue() == null)
                counter++;
        }
        if (counter >= ParkingLotSystem.PARKING_LOT_SIZE)
            return true;
        return false;
    }

}

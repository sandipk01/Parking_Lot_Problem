package com.bridgelabz.code.service;

import com.bridgelabz.code.model.Vehicle;

import java.util.Map;

public class ParkingAttendant extends Driver {

    private static int section = 65;

    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        super(parkingLotSystem);
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

package com.bridgelabz.code.model;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int parkingLotNumber;
    public static final int PARKING_LOT_SIZE = 100;
    private Map<Integer,Vehicle> vehicles;

    public ParkingLot(int parkingLotNumber) {
        vehicles = new HashMap<>();
        this.parkingLotNumber = parkingLotNumber;
    }

    public Map<Integer,Vehicle> getVehicles() {
        return vehicles;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

}

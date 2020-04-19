package com.bridgelabz.code.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int parkingLotNumber;
    public static final int PARKING_LOT_SIZE = 100;
    private List<Vehicle> vehicles;

    public ParkingLot(int parkingLotNumber) {
        vehicles = new ArrayList<>();
        this.parkingLotNumber = parkingLotNumber;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

}

package com.bridgelabz.code.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int parkingLotNumber;
    private int parkingLotSize;
    private List<Vehicle> vehicles;

    public ParkingLot(int parkingLotNumber) {
        vehicles = new ArrayList<>();
        this.parkingLotSize = 100;
        this.parkingLotNumber = parkingLotNumber;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getParkingLotSize() {
        return parkingLotSize;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

}

package com.bridgelabz.code.service;

import com.bridgelabz.code.model.ParkingSign;

public class ParkingLotOwner implements IParkingInquiry {

    private ParkingLotSystem parkingLotSystem;

    public ParkingLotOwner(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public boolean isLotsFull() {
        return parkingLotSystem.isParkingLotsFull();
    }

    @Override
    public ParkingSign parkingSign() {
       return parkingLotSystem.getParkingLotSign();
    }

}

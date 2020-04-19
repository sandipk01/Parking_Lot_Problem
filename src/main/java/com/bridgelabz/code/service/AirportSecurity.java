package com.bridgelabz.code.service;

public class AirportSecurity {
    private ParkingLotSystem parkingLotSystem;

    public AirportSecurity(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    //Parking Lots full then redirect security staff
    public boolean redirectSecurityStaff() {
        return (parkingLotSystem.isParkingLotsFull()) ? true : false;
    }

}

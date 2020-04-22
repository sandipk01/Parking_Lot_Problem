package com.bridgelabz.code.model;

import com.bridgelabz.code.enums.DriverType;

public class ParkingDetails {

    private String parkingSlot;
    private String parkTime;
    private String unParkTime;
    private DriverType driverType;

    public ParkingDetails(String parkingSlot, String parkTime,DriverType driverType) {
        this.parkingSlot = parkingSlot;
        this.parkTime = parkTime;
        this.driverType=driverType;
    }

    public void setUnParkTime(String unParkTime) {
        this.unParkTime = unParkTime;
    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public String getParkTime() {
        return parkTime;
    }

    public String getUnParkTime() {
        return unParkTime;
    }
}

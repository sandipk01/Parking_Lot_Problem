package com.bridgelabz.code.model;

public class ParkingDetails {

    private String parkingSlot;
    private String parkTime;
    private String unParkTime;

    public ParkingDetails(String parkingSlot, String parkTime) {
        this.parkingSlot = parkingSlot;
        this.parkTime = parkTime;
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

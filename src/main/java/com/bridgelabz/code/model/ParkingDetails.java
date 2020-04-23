package com.bridgelabz.code.model;

import com.bridgelabz.code.enums.DriverType;
import com.bridgelabz.code.enums.VehicleType;

public class ParkingDetails {

    private String parkingSlot;
    private String parkTime;
    private String unParkTime;
    private DriverType driverType;
    private VehicleType vehicleType = VehicleType.SMALL_VEHICLE;
    ;

    public ParkingDetails(String parkingSlot, String parkTime, DriverType driverType, VehicleType vehicleType) {
        this(parkingSlot, parkTime, driverType);
        this.vehicleType = vehicleType;
    }

    public ParkingDetails(String parkingSlot, String parkTime, DriverType driverType) {
        this.parkingSlot = parkingSlot;
        this.parkTime = parkTime;
        this.driverType = driverType;
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

    public DriverType getDriverType() {
        return driverType;
    }

    public String getUnParkTime() {
        return unParkTime;
    }
}

package com.bridgelabz.code.model;

import com.bridgelabz.code.enums.DriverType;
import com.bridgelabz.code.enums.VehicleType;

public class ParkingDetails {

    private String parkingSlot;
    private String parkTime;
    private String unParkTime;
    private DriverType driverType;
    private VehicleType vehicleType = VehicleType.SMALL_VEHICLE;
    private String parkingAttendantName;
    private String numberPlat;

    public ParkingDetails(String parkingSlot, String parkTime, DriverType driverType, VehicleType vehicleType,String numberPlat) {
        this(parkingSlot, parkTime, driverType, numberPlat,null);
        this.vehicleType = vehicleType;
    }

    public ParkingDetails(String parkingSlot, String parkTime, DriverType driverType, String numberPlat,String parkingAttendantName) {
        this.parkingSlot = parkingSlot;
        this.parkTime = parkTime;
        this.driverType = driverType;
        this.parkingAttendantName=parkingAttendantName;
        this.numberPlat=numberPlat;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

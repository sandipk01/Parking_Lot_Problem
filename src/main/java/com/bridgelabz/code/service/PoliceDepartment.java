package com.bridgelabz.code.service;

import com.bridgelabz.code.enums.VehicleInquiry;
import com.bridgelabz.code.model.ParkingDetails;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.utils.Utils;

import javax.rmi.CORBA.Util;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoliceDepartment {

    private List<Object> vehicles;
    private ParkingLotSystem parkingLotSystem;

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }


    public List<Object> getParkingDetails(VehicleInquiry vehicleInquiry, Map<String, Vehicle> parkingLot, Map<Vehicle, ParkingDetails> parkingDetailsMap, String... inquiry) throws ParseException {

        switch (vehicleInquiry) {
            case COLOR:
                vehicles = new ArrayList<>();
                for (Vehicle vehicle : parkingLot.values()) {
                    if (vehicle != null && vehicle.getVehicleColor().equals(inquiry[0])) {
                        vehicles.add(vehicle);
                    }
                }
                break;
            case COLOR_AND_BRAND:
                vehicles = new ArrayList<>();
                for (Vehicle vehicle : parkingLot.values()) {
                    if (vehicle != null && vehicle.getVehicleColor().equals(inquiry[0]) && vehicle.getBrandName().equals(inquiry[1])) {
                        vehicles.add(parkingDetailsMap.get(vehicle));
                    }
                }
                break;
            case BRAND_SECURITY:
                vehicles = new ArrayList<>();
                for (Vehicle vehicle : parkingLot.values()) {
                    if (vehicle != null && vehicle.getBrandName().equals(inquiry[0])) {
                        vehicles.add(vehicle);
                        parkingLotSystem.increaseSecurity(true);
                    }
                }
                break;
            case TIME:
                vehicles = new ArrayList<>();
                for (Map.Entry<Vehicle, ParkingDetails> entry : parkingDetailsMap.entrySet()) {
                    String currentTime = Utils.getIncreaseTime(Integer.parseInt(inquiry[0]),Integer.parseInt(inquiry[1]),Integer.parseInt(inquiry[2]));
                    String parkingTime = entry.getValue().getParkTime();
                    if (Utils.getDifferenceInMinutes(parkingTime,currentTime) >= Integer.parseInt(inquiry[2]) && Utils.getDifferenceInMinutes(parkingTime, currentTime) >= 0) {
                        vehicles.add(entry.getKey());
                    }
                }
        }
        return vehicles;
    }


}

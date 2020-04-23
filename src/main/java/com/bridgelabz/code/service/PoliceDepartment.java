package com.bridgelabz.code.service;

import com.bridgelabz.code.enums.VehicleInquiry;
import com.bridgelabz.code.model.ParkingDetails;
import com.bridgelabz.code.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoliceDepartment {

    private List<Object> vehicles;
    private ParkingLotSystem parkingLotSystem;

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public List<Object> getParkingDetails(VehicleInquiry vehicleInquiry, Map<String, Vehicle> parkingLot, Map<Vehicle, ParkingDetails> parkingDetailsMap, String... inquiry) {

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
        }
        return vehicles;
    }

}

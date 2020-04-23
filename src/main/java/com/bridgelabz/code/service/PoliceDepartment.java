package com.bridgelabz.code.service;

import com.bridgelabz.code.enums.VehicleInquiry;
import com.bridgelabz.code.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoliceDepartment {

    private List<Vehicle> vehicles;

    public List<Vehicle> getParkingDetails(VehicleInquiry vehicleInquiry, Map<String, Vehicle> parkingLot, String inquiry) {

        switch (vehicleInquiry) {
            case COLOR:
                vehicles = new ArrayList<>();
                for (Vehicle vehicle : parkingLot.values()) {
                    if (vehicle!=null && vehicle.getVehicleColor().equals(inquiry)) {
                        vehicles.add(vehicle);
                    }
                }
                break;
        }
        return vehicles;
    }

}

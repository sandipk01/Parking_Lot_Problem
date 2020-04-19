package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingLot;
import com.bridgelabz.code.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {

    private int noOfParkingLots;
    private ParkingLot parkingLot;
    private Map<Integer, ParkingLot> parkingLots;
    private int currentParkingLot;

    //Initializing the parking total parking lots
    public ParkingLotSystem(int noOfParkingLots) {
        this.noOfParkingLots = noOfParkingLots;
        generateParkingLot();
    }

    public int getNoOfParkingLots() {
        return noOfParkingLots;
    }

    //Method for creating number of parking lots
    private void generateParkingLot() {
        parkingLots = new HashMap<>();
        for (int parkingLotNumber = 0; parkingLotNumber < noOfParkingLots; parkingLotNumber++) {
            parkingLot = new ParkingLot(parkingLotNumber);
            parkingLots.put(parkingLotNumber, parkingLot);
        }
    }

    //Checking if particular parking lot is full or not
    public boolean isParkingLotFull(int parkingLotNumber) {
        return (getVehicleCounts(parkingLotNumber) == parkingLots.get(parkingLotNumber)
                .getParkingLotSize()) ? true : false;
    }

    //Checking if all parking lots are full
    public boolean isParkingLotsFull() {
        int currentLot = 0;
        while (currentLot != noOfParkingLots) {
            if (isParkingLotFull(currentLot)) {
                currentLot++;
                continue;
            } else {
                currentParkingLot = currentLot;
                return false;
            }
        }
        return true;
    }

    //Method for park a vehicle
    public boolean parkingVehicle(Vehicle vehicle) throws ParkingLotException {
        if (isParkingLotsFull()) {
            throw new ParkingLotException("Parking space is not available",
                    ParkingLotException.TypeOfException.NO_PARKING_SPACE_EXCEPTION);
        } else if (isVehicleParked(vehicle)) {
            System.out.println("Already parked vehicle" + vehicle.getBrandName() + " -- " +
                    vehicle.getModelName() + " -- " + vehicle.getNumberPlate() +
                    " Parked At : parking slot No : "
                    + parkingLots.get(currentParkingLot).getParkingLotNumber());
            return false;
        } else {
            parkingLots.get(currentParkingLot).getVehicles().add(vehicle);
            System.out.println(vehicle.getBrandName() + " -- " + vehicle.getModelName() +
                    " -- " + vehicle.getNumberPlate() + " Parked At : parking slot No : " +
                    parkingLots.get(currentParkingLot).getParkingLotNumber());
        }
        return true;
    }

    //Check vehicle is parked or not
    public boolean isVehicleParked(Vehicle vehicle) {
        for (Map.Entry<Integer, ParkingLot> parkingLotEntry : parkingLots.entrySet()) {
            for (Vehicle vehicle1 : parkingLotEntry.getValue().getVehicles()) {
                if (vehicle1.equals(vehicle)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Un park vehicle
    public boolean unPark(Vehicle vehicle) {
        if (!isVehicleParked(vehicle)) {
            System.out.println(vehicle.getNumberPlate() + " Vehicle Not found");
            return false;
        } else {
            for (Map.Entry<Integer, ParkingLot> parkingLotEntry : parkingLots.entrySet()) {
                parkingLotEntry.getValue().getVehicles().remove(vehicle);
            }
            System.out.println(vehicle.getBrandName() + " -- " + vehicle.getModelName() +
                    " -- " + vehicle.getNumberPlate() + " UN Parked At : parking slot No : " +
                    parkingLots.get(currentParkingLot).getParkingLotNumber());
        }
        return true;
    }

    public int getVehicleCounts(int parkingLotNumber) {
        return parkingLots.get(parkingLotNumber).getVehicles().size();
    }

}

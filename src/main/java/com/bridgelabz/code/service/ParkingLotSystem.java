package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingLot;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.IObserver;
import com.bridgelabz.code.observer.ISubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem implements ISubject {

    private int noOfParkingLots;
    private ParkingLot parkingLot;
    private Map<Integer, ParkingLot> parkingLots;
    private int currentParkingLot;
    private List<IObserver> observerList;

    //Initializing the parking total parking lots
    public ParkingLotSystem(int noOfParkingLots) {
        observerList = new ArrayList<>();
        this.noOfParkingLots = noOfParkingLots;
        generateParkingLot();
    }

    @Override
    public void attach(IObserver iObserver) {
        observerList.add(iObserver);
    }

    @Override
    public void detach(IObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observerList) {
            observer.sendParkingStatus(isParkingLotsFull());
        }

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
                .PARKING_LOT_SIZE) ? true : false;
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
            return false;
        } else if (isVehicleParked(vehicle)) {
            throw new ParkingLotException("Car is Already Parked :", ParkingLotException.TypeOfException.ALREADY_PARKED);
        } else {
            parkingLots.get(currentParkingLot).getVehicles().add(vehicle);
            isParkingLotsFull();
            notifyObservers();
            this.notifyObservers();
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
    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (!isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle not found", ParkingLotException.TypeOfException.VEHICLE_NO_FOUND);
        } else {
            for (Map.Entry<Integer, ParkingLot> parkingLotEntry : parkingLots.entrySet()) {
                parkingLotEntry.getValue().getVehicles().remove(vehicle);
            }
            isParkingLotsFull();
            notifyObservers();
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

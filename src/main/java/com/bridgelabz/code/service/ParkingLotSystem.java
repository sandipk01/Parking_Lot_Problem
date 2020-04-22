package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingDetails;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.IObserver;
import com.bridgelabz.code.observer.ISubject;
import com.bridgelabz.code.utils.Utils;

import java.util.*;

public class ParkingLotSystem implements ISubject {

    private int noOfParkingLots;
    private Map<String, Vehicle> parkingLot;
    private List<IObserver> observerList;
    public static final int PARKING_LOT_SIZE = 100;
    private ParkingAttendant parkingAttendant;
    private ParkingDetails parkingDetails;
    private Map<Vehicle, ParkingDetails> parkingDetailsMap;

    //Initializing the parking total parking lots
    public ParkingLotSystem(int noOfParkingLots) {
        parkingDetailsMap=new LinkedHashMap<>();
        observerList = new ArrayList<>();
        this.noOfParkingLots = noOfParkingLots;
        this.parkingAttendant = new ParkingAttendant(this);
        generateParkingLots();
    }

    public Map<Vehicle, ParkingDetails> getParkingDetailsMap() {
        return parkingDetailsMap;
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
            observer.sendParkingStatus(isParkingLotFull());
        }

    }

    public int getNoOfParkingLots() {
        return noOfParkingLots;
    }

    //Method for creating number of parking lots
    private void generateParkingLots() {
        parkingLot = new LinkedHashMap<>();
        int parkingSection = 65;
        for (int parkingLotNumber = 1; parkingLotNumber <= noOfParkingLots; parkingLotNumber++) {
            for (int parkingVehicleNumber = 1; parkingVehicleNumber <= PARKING_LOT_SIZE; parkingVehicleNumber++) {
                parkingLot.put((char) parkingSection + " " + parkingVehicleNumber, null);
            }
            parkingSection++;
        }
    }

    //Checking if particular parking lot is full or not
    public boolean isParkingLotFull() {
        return (getVehicleCounts() == (PARKING_LOT_SIZE * noOfParkingLots)) ? true : false;
    }

    public Map<Vehicle,ParkingDetails> parkAVehicle(Vehicle vehicle) throws ParkingLotException {
        parkingLot = parkingAttendant.parkAVehicle(vehicle, parkingLot);
        parkingDetails=new ParkingDetails(searchAVehicle(vehicle), Utils.getCurrentTime());
        parkingDetailsMap.put(vehicle,parkingDetails);
        return parkingDetailsMap;
    }

    public Map<Vehicle,ParkingDetails> unParkAVehicle(Vehicle vehicle) throws ParkingLotException {
        parkingLot = parkingAttendant.unParkAVehicle(vehicle, parkingLot);
        parkingDetailsMap.get(vehicle).setUnParkTime(Utils.getCurrentTime());
        return parkingDetailsMap;
    }


    //Check vehicle is parked or not
    public boolean isVehicleParked(Vehicle vehicle) {
        if (parkingLot.containsValue(vehicle)) {
            return true;
        }
        return false;
    }

    public int getVehicleCounts() {
        int count = 0;
        for (Map.Entry<String, Vehicle> parkingLotEntry : parkingLot.entrySet()) {
            if (parkingLotEntry.getValue() != null)
                count++;
        }
        return count;
    }

    public String searchAVehicle(Vehicle vehicle) {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (Objects.equals(vehicle, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void show() {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}

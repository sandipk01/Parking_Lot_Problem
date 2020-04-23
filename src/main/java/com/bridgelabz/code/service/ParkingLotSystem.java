package com.bridgelabz.code.service;

import com.bridgelabz.code.enums.DriverType;
import com.bridgelabz.code.enums.VehicleInquiry;
import com.bridgelabz.code.enums.VehicleType;
import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingDetails;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.IObserver;
import com.bridgelabz.code.observer.ISubject;
import com.bridgelabz.code.utils.Utils;

import java.util.*;

public class ParkingLotSystem implements ISubject {

    private static int noOfParkingLots;
    private Map<String, Vehicle> parkingLot;
    private List<IObserver> observerList;
    public static int PARKING_LOT_SIZE = 100;
    private ParkingAttendant parkingAttendant;
    private ParkingDetails parkingDetails;
    private Map<Vehicle, ParkingDetails> parkingDetailsMap;
    private Driver driver;
    private PoliceDepartment policeDepartment;


    //Initializing the parking total parking lots
    public ParkingLotSystem(int noOfParkingLots) {
        parkingDetailsMap = new LinkedHashMap<>();
        observerList = new ArrayList<>();
        this.noOfParkingLots = noOfParkingLots;
        this.parkingAttendant = new ParkingAttendant(this);
        this.driver = new Driver(this);
        this.policeDepartment=new PoliceDepartment(this);
        generateParkingLots();
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

    public static int getNoOfParkingLots() {
        return noOfParkingLots;
    }

    public Map<String, Vehicle> getParkingLot() {
        return parkingLot;
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

    //parking a vehicle and storing details
    public Map<Vehicle, ParkingDetails> parkAVehicle(Vehicle vehicle, DriverType driverType, VehicleType vehicleType,String parkingAttendantName,String numberPlat) throws ParkingLotException {
        switch (driverType) {
            case NORMAL:
                parkingLot = driver.parkAVehicle(vehicle, parkingLot, vehicleType);
                parkingDetails = new ParkingDetails(searchAVehicle(vehicle), Utils.getCurrentTime(), DriverType.NORMAL, vehicleType,numberPlat);
                parkingDetailsMap.put(vehicle, parkingDetails);
                break;
            case HANDICAP:
                parkingLot = parkingAttendant.parkAVehicle(vehicle, parkingLot);
                parkingDetails = new ParkingDetails(searchAVehicle(vehicle), Utils.getCurrentTime(), DriverType.HANDICAP,numberPlat,parkingAttendantName);
                parkingDetailsMap.put(vehicle, parkingDetails);
        }
        isParkingLotFull();
        notifyObservers();
        return parkingDetailsMap;
    }

    //unPark a vehicle and storing details
    public Map<Vehicle, ParkingDetails> unParkAVehicle(Vehicle vehicle) throws ParkingLotException {
        parkingLot = driver.unParkAVehicle(vehicle, parkingLot);
        parkingDetailsMap.get(vehicle).setUnParkTime(Utils.getCurrentTime());
        isParkingLotFull();
        notifyObservers();
        return parkingDetailsMap;
    }

    public List<Object> getParkingDetails(VehicleInquiry vehicleInquiry, String... inquiry){
        return policeDepartment.getParkingDetails(vehicleInquiry,parkingLot,parkingDetailsMap,inquiry);
    }

    //Check vehicle is parked or not.
    public boolean isVehicleParked(Vehicle vehicle) {
        if (parkingLot.containsValue(vehicle)) {
            return true;
        }
        return false;
    }

    //Getting total number of parked vehicles.
    public int getVehicleCounts() {
        int count = 0;
        for (Map.Entry<String, Vehicle> parkingLotEntry : parkingLot.entrySet()) {
            if (parkingLotEntry.getValue() != null)
                count++;
        }
        return count;
    }

    //Searching vehicle parked in which slot.
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

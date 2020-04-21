package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;

import java.util.Map;

public interface IDriving {

    public Map<String, Vehicle> parkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException;

    public Map<String, Vehicle> unParkAVehicle(Vehicle vehicle, Map<String, Vehicle> parkingLot) throws ParkingLotException;
}

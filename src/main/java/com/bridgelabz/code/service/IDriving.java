package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;

public interface IDriving {

    public boolean parkAVehicle(Vehicle vehicle) throws ParkingLotException;

    public boolean unParkAVehicle(Vehicle vehicle);
}

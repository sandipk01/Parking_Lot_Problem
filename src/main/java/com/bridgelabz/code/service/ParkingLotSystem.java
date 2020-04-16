package com.bridgelabz.code.service;

import com.bridgelabz.code.exception.ParkingLotException;

public class ParkingLotSystem {

    private Object vehicle;

    //Method for park a vehicle
    public boolean park(Object vehicleObject) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("parking space is not available!",ParkingLotException.TypeOfException.NO_PARKING_SPACE_EXCEPTION);
        this.vehicle = vehicleObject;
        return true;
    }

    //Check vehicle is parked or not
    public boolean isVehicleParked(Object vehicleObject){
        return (this.vehicle.equals(vehicleObject)) ? true : false;
    }

    //Vehicle for un Park
    public boolean unPark(Object vehicleObject) {
        if (this.vehicle == null) return false;
        if(this.vehicle==vehicleObject){
            this.vehicle=null;
            return true;
        }
        return false;
    }

}

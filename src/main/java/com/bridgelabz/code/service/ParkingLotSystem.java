package com.bridgelabz.code.service;

public class ParkingLotSystem {

    private Object vehicle;

    //Method for park a vehicle
    public boolean park(Object vehicleObject) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicleObject;
        return true;
    }

    public boolean unPark(Object vehicleObject) {
        if (this.vehicle == null) return false;
        if(this.vehicle==vehicleObject){
            this.vehicle=null;
            return true;
        }
        return false;
    }

}

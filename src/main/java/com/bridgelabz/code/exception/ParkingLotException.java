package com.bridgelabz.code.exception;

public class ParkingLotException extends Exception{
    public enum TypeOfException{
        ALREADY_PARKED,VEHICLE_NO_FOUND;
    }

    public TypeOfException type;

    public ParkingLotException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}

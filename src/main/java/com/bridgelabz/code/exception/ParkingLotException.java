package com.bridgelabz.code.exception;

public class ParkingLotException extends Exception{
    public enum TypeOfException{
        NO_PARKING_SPACE_EXCEPTION;
    }

    public TypeOfException type;

    public ParkingLotException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}

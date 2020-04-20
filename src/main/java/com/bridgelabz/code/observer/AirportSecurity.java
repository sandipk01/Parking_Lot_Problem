package com.bridgelabz.code.observer;

public class AirportSecurity implements IObserver {

    boolean isParkingFull;

    @Override
    public void sendParkingStatus(boolean isFull) {
        isParkingFull = (isFull) ? true : false;
    }

    public boolean redirectSecurityStaff() {
        return isParkingFull;
    }


}

package com.bridgelabz.code.observer;

import com.bridgelabz.code.enums.ParkingSign;

public class ParkingLotOwner implements IObserver {

    private ParkingSign parkingSign;

    public ParkingSign getParkingSign() {
        return parkingSign;
    }

    @Override
    public void sendParkingStatus(boolean isFull) {
        this.parkingSign = (isFull) ? ParkingSign.PARKING_IS_FULL : ParkingSign.PARKING_NOT_FULL;
    }

}

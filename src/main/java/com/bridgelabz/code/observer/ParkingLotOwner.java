package com.bridgelabz.code.observer;

import com.bridgelabz.code.eums.ParkingSign;
import com.bridgelabz.code.model.Vehicle;

import java.util.Map;

public class ParkingLotOwner implements IObserver {

    private ParkingSign parkingSign;

    public ParkingSign getParkingSign() {
        return parkingSign;
    }

    @Override
    public void sendParkingStatus(boolean isFull) {
        this.parkingSign = (isFull) ? ParkingSign.PARKING_IS_FULL : ParkingSign.PARKING_NOT_FULL;
    }

    //Getting empty parking position.
    public String getDecideParkingPosition(Map<String,Vehicle> parkingLot) {
        for (Map.Entry<String, Vehicle> entry : parkingLot.entrySet()) {
            if (entry.getValue()==null) {
                return entry.getKey();
            }
        }
        return null;
    }
}

package com.bridgelabz.code.service;

public class ParkingLotOwner implements IParkingInquiry {

    public enum Sign {PARKING_IS_FULL}

    private Sign sign;

    private ParkingLotSystem parkingLotSystem;

    public ParkingLotOwner(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    @Override
    public boolean isLotsFull() {
        return parkingLotSystem.isParkingLotsFull();
    }

    @Override
    public void parkingSign() {
        if (isLotsFull())
            setSign(Sign.PARKING_IS_FULL);
        setSign(null);
    }

}

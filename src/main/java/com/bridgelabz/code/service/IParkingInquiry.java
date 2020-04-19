package com.bridgelabz.code.service;

import com.bridgelabz.code.model.ParkingSign;

public interface IParkingInquiry {

    public boolean isLotsFull();

    public ParkingSign parkingSign();
}

package com.bridgelabz.test;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    private Object vehicle;
    private ParkingLotSystem parkingLotSystem;

    @Before
    public void setUp() {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        boolean isPark = parkingLotSystem.park(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenVehicle_WhenAlreadyPark_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.isVehicleParked(new Object());
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicle_WhenUnPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.unPark(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenNotParkVehicle_WhenTryUnPark_ShouldReturnFalse() {
        boolean isPark = parkingLotSystem.unPark(vehicle);
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicle_WhenTryToUnParkDifferentVehicle_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.unPark(new Object());
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicleTryPark_WhenParkingSpaceIsFull_ShouldReturnThrowException()  {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.NO_PARKING_SPACE_EXCEPTION, e.type);
        }
    }

}

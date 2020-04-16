package com.bridgelabz.test;

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
    public void givenVehicle_WhenPark_ShouldReturnTrue() {
        boolean isPark = parkingLotSystem.park(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenVehicle_WhenAlreadyPark_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.park(new Object());
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicle_WhenUnPark_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.unPark(vehicle);
        Assert.assertEquals(true, isPark);
    }

}

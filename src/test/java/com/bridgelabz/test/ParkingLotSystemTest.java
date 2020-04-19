package com.bridgelabz.test;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.service.Driver;
import com.bridgelabz.code.service.IDriving;
import com.bridgelabz.code.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    private Vehicle vehicle;
    private ParkingLotSystem parkingLotSystem;
    private int numberOfVehicles = 1;
    private IDriving driver;

    @Before
    public void setUp() {
        vehicle = new Vehicle("BMW", "S1", "MH7845S");
        parkingLotSystem = new ParkingLotSystem(3);
        driver=new Driver(parkingLotSystem);
    }

    @Test
    public void givenVehicle_WhenDriverPark_ShouldReturnTrue() throws ParkingLotException {
        boolean isPark = driver.parkAVehicle(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenVehicle_WhenDriverAlreadyPark_ShouldReturnFalse() throws ParkingLotException {
        driver.parkAVehicle(vehicle);
        boolean isPark = driver.parkAVehicle(vehicle);
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicle_WhenDriverUnPark_ShouldReturnTrue() throws ParkingLotException {
        driver.parkAVehicle(vehicle);
        boolean isPark = driver.unParkAVehicle(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenNotParkVehicle_WhenDriverTryUnPark_ShouldReturnFalse() {
        boolean isPark = driver.unParkAVehicle(vehicle);
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicle_WhenDriverTryToUnParkDifferentVehicle_ShouldReturnFalse() throws ParkingLotException {
        driver.parkAVehicle(vehicle);
        boolean isPark = driver.unParkAVehicle(new Vehicle("mercedes", "S2", "MH8745L"));
        Assert.assertEquals(false, isPark);
    }

    @Test
    public void givenVehicleTryPark_WhenParkingSpaceIsFull_ShouldReturnThrowException() {
        try {
            while (numberOfVehicles <= (parkingLotSystem.getNoOfParkingLots() * 100) + 1) {
                driver=new Driver(parkingLotSystem);
                vehicle = new Vehicle("Skoda", "74t", "MH8885M");
                driver.parkAVehicle(vehicle);
                numberOfVehicles++;
            }
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.NO_PARKING_SPACE_EXCEPTION, e.type);
        }
    }

}

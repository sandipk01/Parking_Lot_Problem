package com.bridgelabz.test;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingSign;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.service.Driver;
import com.bridgelabz.code.service.IDriving;
import com.bridgelabz.code.service.ParkingLotOwner;
import com.bridgelabz.code.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ParkingLotSystemTest {
    private Vehicle vehicle;
    private ParkingLotSystem parkingLotSystem;
    private int numberOfVehicles = 1;
    private IDriving driver;
    private ParkingLotOwner parkingLotOwner;

    @Before
    public void setUp() {
        vehicle = new Vehicle("BMW", "S1", "MH7845S");
        parkingLotSystem = new ParkingLotSystem(3);
        driver = new Driver(parkingLotSystem);
        parkingLotOwner = new ParkingLotOwner(parkingLotSystem);
    }

    @Test
    public void givenVehicle_WhenDriverPark_ShouldReturnTrue() throws ParkingLotException {
        boolean isPark = driver.parkAVehicle(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenVehicle_WhenDriverAlreadyPark_ShouldReturnFalse() {
        try {
            driver.parkAVehicle(vehicle);
            driver.parkAVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.ALREADY_PARKED, e.type);
        }

    }

    @Test
    public void givenVehicle_WhenDriverUnPark_ShouldReturnTrue() throws ParkingLotException {
        driver.parkAVehicle(vehicle);
        boolean isPark = driver.unParkAVehicle(vehicle);
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void givenNotParkVehicle_WhenDriverTryUnPark_ThenShouldThrowException() {
        try {
            driver.unParkAVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.VEHICLE_NO_FOUND, e.type);
        }

    }

    @Test
    public void givenVehicle_WhenDriverTryToUnParkDifferentVehicle_ThenShouldThrowException() {
        try {
            driver.parkAVehicle(vehicle);
            boolean isPark = driver.unParkAVehicle(new Vehicle("mercedes", "S2", "MH8745L"));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.VEHICLE_NO_FOUND, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenParkingSpaceIsFull_ThenShouldOwnerCanPutFullSign() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.getNoOfParkingLots() * 100) + 1) {
            driver = new Driver(parkingLotSystem);
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            driver.parkAVehicle(vehicle);
            numberOfVehicles++;
        }
        Assert.assertEquals(parkingLotOwner.parkingSign(), ParkingSign.PARKING_IS_FULL);
    }

}

package com.bridgelabz.test;

import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.model.ParkingLot;
import com.bridgelabz.code.model.ParkingSign;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ParkingLotSystemTest {
    private Vehicle vehicle;
    private ParkingLot parkingLot;
    private ParkingLotSystem parkingLotSystem;
    private int numberOfVehicles = 1;
    private IDriving driver;
    private ParkingLotOwner parkingLotOwner;
    private AirportSecurity airportSecurity;

    @Before
    public void setUp() {
        vehicle = new Vehicle("BMW", "S1", "MH7845S");
        parkingLotSystem = new ParkingLotSystem(3);
        driver = new Driver(parkingLotSystem);
        parkingLotOwner = new ParkingLotOwner(parkingLotSystem);
        airportSecurity=new AirportSecurity(parkingLotSystem);
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
        while (numberOfVehicles <= (parkingLotSystem.getNoOfParkingLots() * ParkingLot.PARKING_LOT_SIZE)) {
            driver = new Driver(parkingLotSystem);
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            driver.parkAVehicle(vehicle);
            numberOfVehicles++;
        }
        Assert.assertEquals(ParkingSign.PARKING_IS_FULL,parkingLotOwner.parkingSign());
    }

    @Test
    public void givenParkingLotsFull_WhenAirportSecurityRedirectSecurityStaff_ThenShouldReturnTrue() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.getNoOfParkingLots() * ParkingLot.PARKING_LOT_SIZE)) {
            driver = new Driver(parkingLotSystem);
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            driver.parkAVehicle(vehicle);
            numberOfVehicles++;
        }
        Assert.assertEquals(true,airportSecurity.redirectSecurityStaff());
    }

}

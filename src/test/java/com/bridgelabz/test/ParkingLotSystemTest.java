package com.bridgelabz.test;

import com.bridgelabz.code.service.ParkingLotSystem;
import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.eums.ParkingSign;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.AirportSecurity;
import com.bridgelabz.code.observer.ParkingLotOwner;
import com.bridgelabz.code.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ParkingLotSystemTest {
    private Vehicle vehicle;
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
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotSystem.attach(airportSecurity);
        parkingLotSystem.attach(parkingLotOwner);
    }

    @Test
    public void givenVehicle_WhenDriverPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkAVehicle(vehicle);
        Assert.assertEquals(true, parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    public void givenVehicle_WhenDriverAlreadyPark_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkAVehicle(vehicle);
            parkingLotSystem.parkAVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.ALREADY_PARKED, e.type);
        }

    }

    @Test
    public void givenVehicle_WhenDriverUnPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkAVehicle(vehicle);
        parkingLotSystem.unParkAVehicle(vehicle);
        Assert.assertEquals(false, parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    public void givenNotParkVehicle_WhenDriverTryUnPark_ThenShouldThrowException() {
        try {
            parkingLotSystem.unParkAVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.VEHICLE_NO_FOUND, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenDriverTryToUnParkDifferentVehicle_ThenShouldThrowException() {
        try {
            parkingLotSystem.parkAVehicle(vehicle);
            parkingLotSystem.unParkAVehicle(new Vehicle("mercedes", "S2", "MH8745L"));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.VEHICLE_NO_FOUND, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenParkingSpaceIsFull_ThenShouldOwnerCanPutFullSign() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            parkingLotSystem.parkAVehicle(vehicle);
            numberOfVehicles++;
        }
        Assert.assertEquals(ParkingSign.PARKING_IS_FULL, parkingLotOwner.getParkingSign());
    }

    @Test
    public void givenParkingLotsFull_WhenAirportSecurityRedirectSecurityStaff_ThenShouldReturnTrue() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            driver = new Driver(parkingLotSystem);
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            parkingLotSystem.parkAVehicle(vehicle);
            numberOfVehicles++;
        }
        Assert.assertEquals(true, airportSecurity.redirectSecurityStaff());
    }

    @Test
    public void givenVehicle_WhenParkingLotSpaceAgain_ThenShouldReturnNull() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            driver = new Driver(parkingLotSystem);
            vehicle = new Vehicle("Skoda", "74t", "MH8885M");
            parkingLotSystem.parkAVehicle(vehicle);
            if (numberOfVehicles == 165) {
                parkingLotSystem.unParkAVehicle(vehicle);
            }
            numberOfVehicles++;
        }
        System.out.println("lot 0 " + parkingLotSystem.getVehicleCounts());
        Assert.assertEquals(ParkingSign.PARKING_NOT_FULL, parkingLotOwner.getParkingSign());
    }

    @Test
    public void givenVehwewwicle_WhenDriverPark_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D");
        parkingLotSystem.parkAVehicle(vehicle1);
        Vehicle vehicle2 = new Vehicle("Skoda", "Rapid", "MH4755D");
        parkingLotSystem.parkAVehicle(vehicle2);
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D");
        parkingLotSystem.parkAVehicle(vehicle3);
        Vehicle vehicle4 = new Vehicle("Skoda", "Rapid", "MH4755D");
        parkingLotSystem.parkAVehicle(vehicle4);
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D");
        parkingLotSystem.parkAVehicle(vehicle5);

        parkingLotSystem.unParkAVehicle(vehicle5);

        System.out.println("lot 0 " + parkingLotSystem.getVehicleCounts());
        parkingLotSystem.show();


    }

}

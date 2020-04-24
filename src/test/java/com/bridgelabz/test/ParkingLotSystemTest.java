package com.bridgelabz.test;

import com.bridgelabz.code.enums.DriverType;
import com.bridgelabz.code.enums.VehicleInquiry;
import com.bridgelabz.code.enums.VehicleType;
import com.bridgelabz.code.service.ParkingLotSystem;
import com.bridgelabz.code.exception.ParkingLotException;
import com.bridgelabz.code.enums.ParkingSign;
import com.bridgelabz.code.model.Vehicle;
import com.bridgelabz.code.observer.AirportSecurity;
import com.bridgelabz.code.observer.ParkingLotOwner;

import com.bridgelabz.code.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ParkingLotSystemTest {
    private Vehicle vehicle;
    private ParkingLotSystem parkingLotSystem;
    private int numberOfVehicles = 1;
    private ParkingLotOwner parkingLotOwner;
    private AirportSecurity airportSecurity;

    @Before
    public void setUp() {
        vehicle = new Vehicle("BMW", "S1", "MH7845S", "Blue");
        parkingLotSystem = new ParkingLotSystem(3);
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotSystem.attach(airportSecurity);
        parkingLotSystem.attach(parkingLotOwner);
    }

    @Test
    public void givenVehicle_WhenDriverPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Assert.assertEquals(true, parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    public void givenVehicle_WhenDriverAlreadyPark_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.ALREADY_PARKED, e.type);
        }

    }

    @Test
    public void givenVehicle_WhenDriverUnPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        parkingLotSystem.unParkAVehicle(vehicle);
        Assert.assertEquals(false, parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    public void givenVehicleParked_WhenNoSpaceIsAvailable_ThenShouldThrowException() {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            try {
                vehicle = new Vehicle("Skoda", "74t", "MH8885M", "Red");
                parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
                numberOfVehicles++;
            } catch (ParkingLotException e) {
                Assert.assertEquals(ParkingLotException.TypeOfException.NO_PARKING_SPACE, e.type);
            }
        }

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
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
            parkingLotSystem.unParkAVehicle(new Vehicle("mercedes", "S2", "MH8745L", "Yellow"));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.TypeOfException.VEHICLE_NO_FOUND, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenParkingSpaceIsFull_ThenShouldOwnerCanPutFullSign() throws ParkingLotException {

        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            vehicle = new Vehicle("Skoda", "74t", "MH8885M", "Blue");
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
            numberOfVehicles++;
        }
        Assert.assertEquals(ParkingSign.PARKING_IS_FULL, parkingLotOwner.getParkingSign());
    }

    @Test
    public void givenParkingLotsFull_WhenAirportSecurityRedirectSecurityStaff_ThenShouldReturnTrue() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            vehicle = new Vehicle("Skoda", "74t", "MH8885M", "Green");
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
            numberOfVehicles++;
        }
        Assert.assertEquals(true, airportSecurity.redirectSecurityStaff());
    }

    @Test
    public void givenVehicle_WhenParkingLotSpaceAgain_ThenShouldReturnNull() throws ParkingLotException {
        while (numberOfVehicles <= (parkingLotSystem.PARKING_LOT_SIZE * parkingLotSystem.getNoOfParkingLots())) {
            vehicle = new Vehicle("Skoda", "74t", "MH8885M", "White");
            parkingLotSystem.parkAVehicle(vehicle, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
            if (numberOfVehicles == 165) {
                parkingLotSystem.unParkAVehicle(vehicle);
            }
            numberOfVehicles++;
        }
        Assert.assertEquals(ParkingSign.PARKING_NOT_FULL, parkingLotOwner.getParkingSign());
    }

    @Test
    public void givenVehicleParked_WhenSearchVehicleToUnpark_ThenShouldReturnParkingSlot() throws ParkingLotException {
        Vehicle bmw1 = new Vehicle("Bmw", "S5", "MH74558D", "Black");
        parkingLotSystem.parkAVehicle(bmw1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Assert.assertEquals("A 1", parkingLotSystem.searchAVehicle(bmw1));
    }

    @Test
    public void givenVehicle_WhenParkedAndUnparked_ThenShouldParkingAndUnParkingTime() throws ParkingLotException {
        Vehicle bmw = new Vehicle("Bmw", "S5", "MH74558D", "Black");
        Assert.assertEquals(parkingLotSystem.parkAVehicle(bmw, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558").get(bmw).getParkTime(), Utils.getCurrentTime());
        Assert.assertEquals(parkingLotSystem.unParkAVehicle(bmw).get(bmw).getUnParkTime(), Utils.getCurrentTime());
    }


    @Test
    public void givenVehicle_WhenParkingAttendantDistributeSpace_ShouldParkCorrectParkingLot() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Assert.assertEquals("A 1", parkingLotSystem.searchAVehicle(vehicle1));
        Assert.assertEquals("B 1", parkingLotSystem.searchAVehicle(vehicle2));
        Assert.assertEquals("C 1", parkingLotSystem.searchAVehicle(vehicle3));
        Assert.assertEquals("A 2", parkingLotSystem.searchAVehicle(vehicle4));
    }

    @Test
    public void givenVehicle_WhenParkingDriverHandicap_ThenShouldParkAttendantParkNearestSlot() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle5, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle6 = new Vehicle("Skoda", "Rapid", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle6, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle7 = new Vehicle("Skoda", "Rapid", "MH4755D", "Green");
        parkingLotSystem.parkAVehicle(vehicle7, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Assert.assertEquals("A 1", parkingLotSystem.searchAVehicle(vehicle1));
        Assert.assertEquals("A 2", parkingLotSystem.searchAVehicle(vehicle2));
        Assert.assertEquals("B 1", parkingLotSystem.searchAVehicle(vehicle3));
        Assert.assertEquals("C 1", parkingLotSystem.searchAVehicle(vehicle4));
        Assert.assertEquals("B 2", parkingLotSystem.searchAVehicle(vehicle5));
        Assert.assertEquals("C 2", parkingLotSystem.searchAVehicle(vehicle6));
        Assert.assertEquals("A 3", parkingLotSystem.searchAVehicle(vehicle7));

    }

    @Test
    public void givenVehicle_WhenIsLarge_ThenShouldParkAttendantParkMaximumSpaceLot() throws ParkingLotException {

        for (int index = 1; index <= 50; index++) {
            Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Green");
            parkingLotSystem.parkAVehicle(vehicle1, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        }

        for (int index = 1; index <= 70; index++) {
            Vehicle vehicle2 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
            parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        }

        Vehicle vehicle4 = new Vehicle("Skoda", "Rapid", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");

        Assert.assertEquals("C 1", parkingLotSystem.searchAVehicle(vehicle4));
    }

    @Test
    public void givenVehicleParked_WhenPoliceInquiryWhiteColorVehicles_ThenShouldReturnListWhileCarLocation() throws ParkingLotException, ParseException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle5, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle6 = new Vehicle("Skoda", "Rapid", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle6, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle7 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle7, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        List<Object> vehicles = parkingLotSystem.getParkingDetails(VehicleInquiry.COLOR, "White");
        Assert.assertEquals(2, vehicles.size());
    }


    @Test
    public void givenVehicleParked_WhenPoliceInquiryColorAndBrand_ThenShouldReturnListOfParkingDetails() throws ParkingLotException, ParseException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("Toyota", "X2", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Toyota", "X5", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle5, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle6 = new Vehicle("Toyota", "X15", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle6, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle7 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle7, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        List<Object> vehicles = parkingLotSystem.getParkingDetails(VehicleInquiry.COLOR_AND_BRAND, "Blue", "Toyota");
        Assert.assertEquals(3, vehicles.size());
    }

    @Test
    public void givenVehicleParked_WhenPoliceInquiryBMW_ThenShouldReturnListOfParkingDetailsAndIncreaseSecurity() throws ParkingLotException, ParseException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("BMW", "X2", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Toyota", "X5", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle5, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle6 = new Vehicle("Toyota", "X15", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle6, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle7 = new Vehicle("BMW", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle7, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        List<Object> vehicles = parkingLotSystem.getParkingDetails(VehicleInquiry.BRAND_SECURITY, "BMW");
        Assert.assertEquals(2, vehicles.size());
        Assert.assertEquals(true, parkingLotSystem.isSecurityIncrease());
    }

    @Test
    public void givenVehicleParked_WhenPoliceInquiryLast30MinuteParkedVehicle_ThenShouldReturnListOfParkingDetailsVehicle() throws ParkingLotException, ParseException {
        Vehicle vehicle1 = new Vehicle("Skoda", "Rapid", "MH4755D", "Yellow");
        parkingLotSystem.parkAVehicle(vehicle1, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle2 = new Vehicle("BMW", "X2", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle2, DriverType.HANDICAP, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle3 = new Vehicle("Skoda", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle3, DriverType.NORMAL, VehicleType.LARGE_VEHICLE, "ABC", "474558");
        Vehicle vehicle4 = new Vehicle("Toyota", "X5", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle4, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle5 = new Vehicle("Skoda", "Rapid", "MH4755D", "White");
        parkingLotSystem.parkAVehicle(vehicle5, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle6 = new Vehicle("Toyota", "X15", "MH4755D", "Blue");
        parkingLotSystem.parkAVehicle(vehicle6, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        Vehicle vehicle7 = new Vehicle("BMW", "Rapid", "MH4755D", "Black");
        parkingLotSystem.parkAVehicle(vehicle7, DriverType.NORMAL, VehicleType.SMALL_VEHICLE, "ABC", "474558");
        List<Object> vehicles = parkingLotSystem.getParkingDetails(VehicleInquiry.TIME, Utils.getCurrentTimeInHours(), Utils.getCurrentTimeInMinutes(), "30");
        Assert.assertEquals(7, vehicles.size());
    }

}

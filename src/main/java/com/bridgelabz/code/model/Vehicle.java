package com.bridgelabz.code.model;

public class Vehicle {

    private String brandName;
    private String modelName;
    private String numberPlate;
    private String vehicleColor;

    public Vehicle(String brandName, String modelName, String numberPlate,String vehicleColor) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.numberPlate = numberPlate;
        this.vehicleColor=vehicleColor;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }
}

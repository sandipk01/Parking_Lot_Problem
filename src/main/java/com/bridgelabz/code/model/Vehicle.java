package com.bridgelabz.code.model;

import java.util.Objects;

public class Vehicle {

    private String brandName;
    private String modelName;
    private String numberPlate;

    public Vehicle(String brandName, String modelName, String numberPlate) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.numberPlate = numberPlate;
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

}

package com.galvanize.simpleautos;

public class Automobile {
    private int year;
    private String make;
    private String model;
    private String color;
    private String owner;
    private String vin;

    public int getYear() {
        return year;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getVin() {
        return vin;
    }

    public Automobile(int year, String make, String model, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }
}

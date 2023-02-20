package com.galvanize.simpleautos;

public class Automobile {
    private int year;
    private String make;
    private String model;
    private Colors color;
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
    public Colors getColor() {
        return color;
    }
    public void setColor(Colors color) {
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

    public Automobile(int year, String make, String model, Colors color, String owner, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.vin = vin;
    }
}

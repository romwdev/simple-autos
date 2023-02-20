package com.galvanize.simpleautos;

public class UpdateAuto {
    private Colors color;
    private String owner;

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

    public UpdateAuto(Colors color, String owner) {
        this.color = color;
        this.owner = owner;
    }
}

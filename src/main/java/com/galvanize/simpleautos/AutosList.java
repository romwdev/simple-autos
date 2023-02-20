package com.galvanize.simpleautos;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    private List<Automobile> automobiles;

    public List<Automobile> getAutomobiles() {
        return automobiles;
    }

    public AutosList() {
        this.automobiles = new ArrayList<>();
        addAuto(new Automobile(1967, "Ford", "Mustang", Colors.RED, "John Doe", "7F03Z01025"));
    }

    public void addAuto(Automobile auto) {
        automobiles.add(auto);
    }

    public void deleteAuto(Automobile auto) {
        automobiles.remove(auto);
    }
}

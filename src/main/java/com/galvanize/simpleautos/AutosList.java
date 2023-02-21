package com.galvanize.simpleautos;

import java.util.ArrayList;
import java.util.List;

public class AutosList {
    private List<Automobile> automobiles;

    public AutosList(List<Automobile> automobiles) {
        this.automobiles = new ArrayList<>(automobiles);
    }
    public AutosList() {
        this.automobiles = new ArrayList<>();
    }

    public List<Automobile> getAutomobiles() {
        return automobiles;
    }

    public boolean isEmpty() {
        return automobiles.isEmpty();
    }


}

package com.galvanize.simpleautos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutosService {
    AutosRepository autosRepository;

    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos() {
        return new AutosList(autosRepository.findAll());
    }

    public AutosList getAutos(String color, String make) {
        List<Automobile> automobiles = autosRepository.findByColorContainsAndMakeContains(color, make);
        if (!automobiles.isEmpty()) {
            return new AutosList(automobiles);
        }
        return null;
    }

    public Automobile addAuto(Automobile auto) {
        return autosRepository.save(auto);
    }

    public Automobile getAuto(String vin) {
        return autosRepository.findByVin(vin).orElse(null);
    }

    public Automobile updateAuto(String vin, String color, String owner) {
        Optional<Automobile> oAutomobile = autosRepository.findByVin(vin);
        if (oAutomobile.isEmpty()) {
            return null;
        }
        oAutomobile.get().setColor(color);
        oAutomobile.get().setOwner(owner);
        return autosRepository.save(oAutomobile.get());
    }

    public void deleteAuto(String vin) {

    }
}

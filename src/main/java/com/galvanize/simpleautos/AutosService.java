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
        List<Automobile> automobiles;
        if (color == null) {
            automobiles = autosRepository.findByMake(make);
        } else if (make == null) {
            automobiles = autosRepository.findByColor(color);
        } else {
            automobiles = autosRepository.findByColorAndMake(color, make);
        }
        return !automobiles.isEmpty() ? new AutosList(automobiles) : new AutosList();
    }

    public Automobile addAuto(Automobile auto) {
        return autosRepository.save(auto);
    }

    public Automobile getAuto(String vin) {
        return autosRepository.findByVin(vin).orElse(new Automobile());
    }

    public Automobile updateAuto(String vin, String color, String owner) {
        Optional<Automobile> oAutomobile = autosRepository.findByVin(vin);
        if (oAutomobile.isEmpty()) {
            return new Automobile();
        }
        oAutomobile.get().setColor(color);
        oAutomobile.get().setOwner(owner);
        return autosRepository.save(oAutomobile.get());
    }

    public void deleteAuto(String vin) {
        Optional<Automobile> oAutomobile = autosRepository.findByVin(vin);
        if (oAutomobile.isPresent()) {
            autosRepository.delete(oAutomobile.get());
        } else {
            throw new AutoNotFoundException();
        }
    }
}

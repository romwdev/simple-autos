package com.galvanize.simpleautos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/autos")
public class AutosController {
    List<Automobile> automobiles = new ArrayList<>();

    public AutosController() {
        automobiles.add(new Automobile(1967, "Ford", "Mustang", Colors.RED, "John Doe", "7F03Z01025"));
    }

    @GetMapping("/{vin}")
    public Automobile getAutoByVin(@PathVariable String vin) {
        for (Automobile auto : automobiles) {
            if (auto.getVin().equals(vin)) {
                return auto;
            }
        }
        return null;
    }
}

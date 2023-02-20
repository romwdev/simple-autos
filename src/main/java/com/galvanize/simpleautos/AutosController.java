package com.galvanize.simpleautos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public List<Automobile> getAllAutos(@RequestParam(required = false) Colors color, @RequestParam(required = false) String make) {
        if (color == null && make == null) {
            return automobiles;
        }
        List<Automobile> searchResults = new ArrayList<>();

        for (Automobile auto : automobiles) {
            if (color == null) {
                if (auto.getMake().equals(make)) {
                    searchResults.add(auto);
                }
            } else if (make == null) {
                if (auto.getColor() == color) {
                    searchResults.add(auto);
                }
            } else {
                if (auto.getColor() == color && auto.getMake().equals(make)) {
                    searchResults.add(auto);
                }
            }
        }
        return searchResults;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToAutos(@RequestBody Automobile auto) {
        automobiles.add(auto);
    }

    @PatchMapping("/{vin}")
    public void updateAuto(@PathVariable String vin, @RequestBody UpdateAuto update) {
        for (Automobile auto : automobiles) {
            if (auto.getVin().equals(vin)) {
                auto.setColor(update.getColor());
                auto.setOwner(update.getOwner());
            }
        }
    }
}

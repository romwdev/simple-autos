package com.galvanize.simpleautos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/autos")
public class AutosController {
    AutosList automobiles = new AutosList();

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping()
    public ResponseEntity<AutosList> getAllAutos(@RequestParam(required = false) String color,
                                                 @RequestParam(required = false) String make) {
        AutosList autosList;
        if (color == null && make == null) {
            autosList = autosService.getAutos();
        } else {
            autosList = autosService.getAutos(color, make);
        }
        return autosList.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(autosList);

    }
}

package com.galvanize.simpleautos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class AutosControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final String path = "/api/autos";

    @Test
    public void getRequestWithVinReturnsAuto() throws Exception {
        mockMvc.perform(get(path + "/7F03Z01025"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(1967))
                .andExpect(jsonPath("$.make").value("Ford"))
                .andExpect(jsonPath("$.model").value("Mustang"))
                .andExpect(jsonPath("$.color").value("RED"))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.vin").value("7F03Z01025"));
    }
}

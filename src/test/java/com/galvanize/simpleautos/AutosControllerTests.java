package com.galvanize.simpleautos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class AutosControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "/api/autos";

    @Test
    public void postRequestAddsAuto() throws Exception {
        Automobile auto = new Automobile(2017, "Honda", "Civic", Colors.BLACK, "Robert Taylor", "AAAAAAA");
        mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auto)))
                .andExpect(status().isCreated());
        mockMvc.perform(get(path + "/AAAAAAA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(2017))
                .andExpect(jsonPath("$.make").value("Honda"))
                .andExpect(jsonPath("$.model").value("Civic"))
                .andExpect(jsonPath("$.color").value("BLACK"))
                .andExpect(jsonPath("$.owner").value("Robert Taylor"))
                .andExpect(jsonPath("$.vin").value("AAAAAAA"));
    }
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

    @Test
    public void getRequestWithNoVinOrParamsReturnsAllAutos() throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].vin").value("7F03Z01025"));
    }

    @Test
    public void getRequestWithNoVinSearchesByParams() throws Exception {
        mockMvc.perform(get(path)
                .param("color", "RED")
                .param("make", "Ford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].vin").value("7F03Z01025"));
    }

    @Test
    public void patchRequestUpdatesColorAndOwner() throws Exception {
        UpdateAuto update = new UpdateAuto(Colors.BLUE, "Bobert");
        mockMvc.perform(patch(path + "/AAAAAAA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk());
        mockMvc.perform(get(path + "/AAAAAAA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("BLUE"))
                .andExpect(jsonPath("$.owner").value("Bobert"));

    }

}

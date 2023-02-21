package com.galvanize.simpleautos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class AutosControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AutosService autosService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "/api/autos";

    @Test
    void getRequestWithNoVinOrParamsReturnsAllAutos() throws Exception {
        List<Automobile> automobiles = populateAutos();
        when(autosService.getAutos()).thenReturn(new AutosList(automobiles));
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getRequestNoParamsReturnsNothing() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList());
        mockMvc.perform(get(path))
                .andExpect(status().isNoContent());
    }

    @Test
    void getRequestWithSearchParamsReturnsList() throws Exception {
        List<Automobile> automobiles = populateAutos();
        when(autosService.getAutos(anyString(), anyString())).thenReturn(new AutosList(automobiles));
        mockMvc.perform(get(path)
                .param("color", "RED")
                .param("make", "Ford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getRequestWithColorRedReturnsRedAutos() throws Exception {
        List<Automobile> automobiles = populateAutos();
        for (int i = 0; i < 5; i++) {
            automobiles.get(i).setColor("RED");
        }
        when(autosService.getAutos("RED", null)).thenReturn(new AutosList(automobiles));
        mockMvc.perform(get(path)
                .param("color", "RED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }


    private List<Automobile> populateAutos() {
        List<Automobile> automobiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            automobiles.add(new Automobile(1967 + i, "Ford", "Mustang", "7F03Z01025" + i));
        }
        return automobiles;
    }

}

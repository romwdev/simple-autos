package com.galvanize.simpleautos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTests {

    private AutosService autosService;
    @Mock
    private AutosRepository autosRepository;
    private Automobile automobile;

    @BeforeEach
    void setUp() {
        autosService = new AutosService(autosRepository);
        automobile = new Automobile(1967, "Ford", "Mustang", "AABBCC");
    }

    @Test
    void getAutosNoParamsReturnsList() {
        when(autosRepository.findAll()).thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos();
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
    }

    @Test
    void getAutosSearchReturnsList() {
        automobile.setColor("RED");
        when(autosRepository.findByColorAndMake(anyString(), anyString()))
                .thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos("RED", "Ford");
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
    }

    @Test
    void getAutosSearchByColorReturnsList() {
        automobile.setColor("RED");
        when(autosRepository.findByColor(anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos("RED", null);
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
    }

    @Test
    void addAutoReturnsAuto() {
        when(autosRepository.save(any(Automobile.class)))
                .thenReturn(automobile);
        Automobile auto = autosService.addAuto(automobile);
        assertThat(auto).isNotNull();
        assertThat(auto.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void addAutoBadContentReturnsBadRequest() {
        Automobile badAuto = new Automobile();
        when(autosRepository.save(any(Automobile.class)))
                .thenReturn(badAuto);
        Automobile auto = autosService.addAuto(badAuto);
        assertThat(auto.getVin()).isNull();
    }

    @Test
    void getAutoByVin() {
        when(autosRepository.findByVin(anyString())).thenReturn(Optional.ofNullable(automobile));
        Automobile auto = autosService.getAuto(automobile.getVin());
        assertThat(auto).isNotNull();
        assertThat(auto.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void updateAuto() {
        when(autosRepository.findByVin(anyString())).thenReturn(Optional.ofNullable(automobile));
        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);
        Automobile auto = autosService.updateAuto(automobile.getVin(), "BLUE", "Robert Taylor");
        assertThat(auto).isNotNull();
        assertThat(auto.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void deleteAuto() {
        when(autosRepository.findByVin(anyString())).thenReturn(Optional.ofNullable(automobile));
        autosService.deleteAuto(automobile.getVin());
        verify(autosRepository).delete(any(Automobile.class));
    }

    @Test
    void deleteAutoNotExists() {
        when(autosRepository.findByVin(anyString())).thenReturn(Optional.empty());
        assertThatExceptionOfType(AutoNotFoundException.class)
                .isThrownBy(() -> autosService.deleteAuto("NOTEXISTSVIN"));
    }
}
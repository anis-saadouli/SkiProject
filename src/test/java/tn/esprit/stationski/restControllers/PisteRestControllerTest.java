package tn.esprit.stationski.restControllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.stationski.entities.Piste;
import tn.esprit.stationski.services.IPisteService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class PisteRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPisteService iPisteService;

    @InjectMocks
    private PisteRestController pisteRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pisteRestController).build();
    }

    @Test
    void testAddPiste() throws Exception {
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        when(iPisteService.addPiste(any(Piste.class))).thenReturn(piste);

        mockMvc.perform(post("/addPiste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1)); // Correction ici
    }


    @Test
    void testUpdatePiste() throws Exception {
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        when(iPisteService.updatePiste(any(Piste.class))).thenReturn(piste);

        mockMvc.perform(put("/updatePiste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1)); // Correction ici
    }

    @Test
    void testRetrieveAllPiste() throws Exception {
        List<Piste> pistes = Arrays.asList(new Piste(), new Piste());
        when(iPisteService.retrieveAllPiste()).thenReturn(pistes);

        mockMvc.perform(get("/getAllPiste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testRetrievePisteById() throws Exception {
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        when(iPisteService.retrievePisteById(1L)).thenReturn(piste);

        mockMvc.perform(get("/getPByID/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numPiste").value(1)); // Correction ici
    }

    @Test
    void testDeletePisteById() throws Exception {
        doNothing().when(iPisteService).deletePisteById(1L);

        mockMvc.perform(delete("/deletePByID/1"))
                .andExpect(status().isOk());

        verify(iPisteService, times(1)).deletePisteById(1L);
    }
}
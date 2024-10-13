package tn.esprit.stationski.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.stationski.entities.Piste;
import tn.esprit.stationski.services.IPisteService;

import java.util.List;

@AllArgsConstructor
@RestController
public class PisteRestController {

    private IPisteService iPisteService;

    @PostMapping("/addPiste")
    public Piste addPiste(@RequestBody Piste piste){
        return iPisteService.addPiste(piste);

    }
    @PutMapping("/updatePiste")
    public Piste updatePiste(@RequestBody Piste piste){
        return iPisteService.updatePiste(piste);

    }
    @GetMapping("/getAllPiste")
    public List<Piste> retrieveAllPiste(){
        return iPisteService.retrieveAllPiste();

    }
    @GetMapping("/getPByID/{idP}")
    public Piste retrievePisteById(@PathVariable Long idP){
        return iPisteService.retrievePisteById(idP);

    }
    @DeleteMapping("/deletePByID/{idP}")
    public void deletePisteById(@PathVariable Long idP){
        iPisteService.deletePisteById(idP);

    }
}
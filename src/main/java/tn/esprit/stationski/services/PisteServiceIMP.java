package tn.esprit.stationski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.stationski.entities.Piste;
import tn.esprit.stationski.repositories.PisteRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class PisteServiceIMP implements IPisteService{

    private PisteRepository pisteRepository;

    @Override
    public Piste addPiste(Piste piste) {
        return pisteRepository.save(piste);
    }

    @Override
    public Piste updatePiste(Piste piste) {
        return pisteRepository.save(piste);
    }

    @Override
    public List<Piste> retrieveAllPiste() {
        return pisteRepository.findAll();
    }

    @Override
    public Piste retrievePisteById(Long idP) {
        return pisteRepository.findById(idP).orElse(null);
    }

    @Override
    public void deletePisteById(Long idP) {
        pisteRepository.deleteById(idP);
    }
}

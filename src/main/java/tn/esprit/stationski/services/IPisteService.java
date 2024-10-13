package tn.esprit.stationski.services;

import tn.esprit.stationski.entities.Piste;

import java.util.List;

public interface IPisteService {
    public Piste addPiste(Piste piste);
    public Piste updatePiste(Piste piste);
    public List<Piste> retrieveAllPiste();
    public Piste retrievePisteById(Long idP);
    public void deletePisteById(Long idP);

}

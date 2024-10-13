package tn.esprit.stationski.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Skieur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numSK;
    private String nomSk;
    private String prenomSk;
    private LocalDate dateNaiss;
    private String ville;
    @OneToOne(cascade = CascadeType.ALL)
    private Abonnement abonnement;
    @OneToMany(mappedBy = "skieur")
    private Set<Inscription> inscriptions;
    @ManyToMany
    private List<Piste> pistes;
}

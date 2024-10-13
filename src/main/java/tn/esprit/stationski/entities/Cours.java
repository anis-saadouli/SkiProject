package tn.esprit.stationski.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class    Cours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numC;
    private int niveau;
    @Enumerated(EnumType.STRING)
    private  TypeCours typeC;
    @Enumerated(EnumType.STRING)
    private  Support support;
    private float prix;
    private int creneau;
    @OneToMany(mappedBy = "cours")
    private List<Inscription> inscriptions;
}

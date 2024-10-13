package tn.esprit.stationski.entities;
import jakarta.persistence.EnumType;
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
@Entity //1
public class Piste implements Serializable {
    @Id //2
    @GeneratedValue(strategy = GenerationType.IDENTITY) //3
    private long numPiste;
    private String monPiste;
    @Enumerated(EnumType.STRING) //4
    private Couleur couleur;

    private int longueur;
    private int pente;
    @ManyToMany(mappedBy = "pistes")
    private List<Skieur> skieurs;
}

package tn.esprit.stationski.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Moniteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numM;
    private String nomM;
    private String prenomM;
    private LocalDate dateRecrut;
    @OneToMany
    private List<Cours> cours;
}

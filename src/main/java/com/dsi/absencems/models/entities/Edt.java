package com.dsi.absencems.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "edt")
public class Edt {
    @Id
    @Column(name = "id_edt", nullable = false)
    private Integer id;

    @Column(name = "datecour")
    private Instant datecour;

    @Column(name = "codemploi", length = 20)
    private String codemploi;

    @Column(name = "enseignant", length = 100)
    private String enseignant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance")
    private SeanceEdt seance;

    @Column(name = "nom_classe")
    private String nomClasse;

    @Column(name = "designation", length = 500)
    private String designation;

    @Column(name = "code_module", length = 20)
    private String codeModule;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "annee_deb", length = 4)
    private String anneeDeb;

}
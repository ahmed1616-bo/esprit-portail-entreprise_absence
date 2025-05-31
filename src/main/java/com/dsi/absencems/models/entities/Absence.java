package com.dsi.absencems.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entete_absence_id")
    private EnteteAbsence enteteAbsence;

    @Column(name = "observation")
    private String observation;

    @Column(name = "date_saisie")
    private Timestamp dateSaisie;

    @Column(name = "date_modify")
    private Timestamp dateModify;

    @Column(name = "type_absence", length = 50)
    private String typeAbsence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etudiant")
    private Etudiant etudiant;

}
package com.dsi.absencems.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @Column(name = "etudiant_id", nullable = false, length = 10)
    private String etudiantId;

    @Column(name = "email_etudiant", length = 100)
    private String emailEtudiant;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "num_cin", length = 8)
    private String numCin;

    @Column(name = "num_passeport", length = 20)
    private String numPasseport;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @Column(name = "code_nationalite", length = 4)
    private String codeNationalite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entreprise_id")
    @JsonIgnore
    private Entreprise entreprise;
}
package com.dsi.absencems.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entreprise_id", nullable = false)
    private Integer id;

    @Column(name = "nom_entreprise", nullable = false, length = 100)
    private String nomEntreprise;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "matricule_fiscale", length = 15)
    private String matriculeFiscale;

    @Column(name = "secteur_activite", length = 100)
    private String secteurActivite;

    @Column(name = "adresse_siege")
    private String adresseSiege;

    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Column(name = "ville", length = 100)
    private String ville;

    @Column(name = "pays", length = 100)
    private String pays;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "email_entreprise", length = 100)
    private String emailEntreprise;

    @Column(name = "site_web", length = 100)
    private String siteWeb;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "capital_social", precision = 15, scale = 2)
    private BigDecimal capitalSocial;

    @Column(name = "chiffre_affaires", precision = 15, scale = 2)
    private BigDecimal chiffreAffaires;

    @Column(name = "nombre_employes")
    private Integer nombreEmployes;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "date_mise_a_jour")
    private Instant dateMiseAJour;

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    private List<Etudiant> etudiants;

}
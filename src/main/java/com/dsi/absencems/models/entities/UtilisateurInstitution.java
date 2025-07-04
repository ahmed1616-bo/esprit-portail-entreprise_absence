package com.dsi.absencems.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "utilisateur_institution")
public class UtilisateurInstitution {
    @Id
    @Column(name = "utilisateur_institution_id", nullable = false)
    private Integer id;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "status")
    private boolean state;

    @Column(name = "date_creation")
    private Instant dateCreation;

    @Column(name = "date_mise_a_jour")
    private Instant dateMiseAJour;

    @Column(name = "email")
    private String email;

}
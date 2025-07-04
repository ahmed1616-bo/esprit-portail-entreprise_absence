package com.dsi.absencems.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "seance_edt")
public class SeanceEdt {
    @Id
    @Column(name = "codeseance", nullable = false, length = 20)
    private String codeseance;

    @Column(name = "heuredeb")
    private LocalTime heuredeb;

    @Column(name = "heurefin")
    private LocalTime heurefin;

}
package com.dsi.absencems.services;

import com.dsi.absencems.models.DTOs.AbsenceEnteteRequest;
import com.dsi.absencems.models.entities.Absence;

import java.util.List;

public interface AbsenceService {
    void ajouterAbsence(AbsenceEnteteRequest request);
    void modifierAbsence(Integer idAbsence, AbsenceEnteteRequest request);
   List<Absence> findAbsencesByNomClasseByEntreprise(String nomClasse,Integer idEnreprise);
   List<Absence> findAbsencesByNomClasse(String nomClasse);

}

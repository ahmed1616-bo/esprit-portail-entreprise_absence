package com.dsi.absencems.services;

import com.dsi.absencems.models.DTOs.AbsenceEnteteRequest;
import com.dsi.absencems.models.entities.Absence;
import com.dsi.absencems.models.entities.EnteteAbsence;
import com.dsi.absencems.repository.AbsenceRepo;
import com.dsi.absencems.repository.EnteteAbsenceRepo;
import com.dsi.absencems.repository.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService{

    @Autowired
    AbsenceRepo absenceRepo;

    @Autowired
    EnteteAbsenceRepo enteteAbsenceRepo;

    @Autowired
    EtudiantRepo etudiantRepo;
    @Override
    public void ajouterAbsence(AbsenceEnteteRequest request) {
        Absence absence = request.getAbsence();
        EnteteAbsence enteteAbsence = request.getEnteteAbsence();

        if (absence == null || enteteAbsence == null) {
            throw new IllegalArgumentException("L'absence et l'entête d'absence ne peuvent pas être nuls.");
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        absence.setDateSaisie(currentTimestamp);
        EnteteAbsence savedEnteteAbsence = enteteAbsenceRepo.save(enteteAbsence);
        absence.setEnteteAbsence(savedEnteteAbsence);
        absenceRepo.save(absence);
    }

    @Override
    public void modifierAbsence(Integer idAbsence, AbsenceEnteteRequest request) {
        Absence existingAbsence = absenceRepo.findById(idAbsence)
                .orElseThrow(() -> new IllegalArgumentException("Absence avec l'ID " + idAbsence + " n'existe pas."));
        // Vérifier si l'entête d'absence existe et est associé correctement
        EnteteAbsence existingEnteteAbsence = existingAbsence.getEnteteAbsence();
        if (existingEnteteAbsence == null) {
            throw new IllegalArgumentException("L'entête d'absence fourni ne correspond pas à l'absence existante.");
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        existingAbsence.setDateModify(currentTimestamp);
        existingAbsence.setObservation(request.getAbsence().getObservation());
        existingAbsence.setTypeAbsence(request.getAbsence().getTypeAbsence());


        enteteAbsenceRepo.save(existingEnteteAbsence);
        absenceRepo.save(existingAbsence);
    }

    @Override
    public List<Absence> findAbsencesByNomClasseByEntreprise(String nomClasse,Integer idEnreprise) {
        return absenceRepo.findAbsencesByNomClasseByentreprise(nomClasse,idEnreprise);
    }

    @Override
    public List<Absence> findAbsencesByNomClasse(String nomClasse) {
        return absenceRepo.findAbsencesByNomClasse(nomClasse);
    }

}
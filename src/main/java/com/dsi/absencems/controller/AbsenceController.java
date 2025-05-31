package com.dsi.absencems.controller;

import com.dsi.absencems.models.DTOs.AbsenceEnteteRequest;
import com.dsi.absencems.models.entities.Absence;
import com.dsi.absencems.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AbsenceController {

    @Autowired
    AbsenceService absenceService;


    @PostMapping("/saisie")
    public ResponseEntity<String> ajouterAbsenceEtEntete(@RequestBody AbsenceEnteteRequest request) {
        try {
            absenceService.ajouterAbsence(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Absence ajoutée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'ajout de l'absence : " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> modifierAbsence(@PathVariable("id") Integer id, @RequestBody AbsenceEnteteRequest request) {
        try {
            absenceService.modifierAbsence(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Absence modifiée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'ajout de l'absence : " + e.getMessage());
        }
    }

    @GetMapping("/{nomClasse}/{idEnreprise}")
    public ResponseEntity<List<Absence>> findAbsencesByNomClasseByEntreprise(@PathVariable String nomClasse,@PathVariable Integer idEnreprise) {
        List<Absence> absences = absenceService.findAbsencesByNomClasseByEntreprise(nomClasse,idEnreprise);
        if (absences.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(absences);
    }

    @GetMapping("/{nomClasse}")
    public ResponseEntity<List<Absence>> findAbsencesByNomClasse(@PathVariable String nomClasse) {
        List<Absence> absences = absenceService.findAbsencesByNomClasse(nomClasse);
        if (absences.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(absences);
    }
}

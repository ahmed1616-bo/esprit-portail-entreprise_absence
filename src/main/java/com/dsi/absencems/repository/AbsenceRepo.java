package com.dsi.absencems.repository;

import com.dsi.absencems.models.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbsenceRepo extends JpaRepository<Absence,Integer> {
    @Query("SELECT a FROM Absence a WHERE a.enteteAbsence.edt.nomClasse = :nomClasse and a.etudiant.entreprise.id = :idEnreprise")
    List<Absence> findAbsencesByNomClasseByentreprise(@Param("nomClasse") String nomClasse, @Param("idEnreprise") Integer idEnreprise);

    @Query("SELECT a FROM Absence a WHERE a.enteteAbsence.edt.nomClasse = :nomClasse")
    List<Absence> findAbsencesByNomClasse(@Param("nomClasse") String nomClasse);
}
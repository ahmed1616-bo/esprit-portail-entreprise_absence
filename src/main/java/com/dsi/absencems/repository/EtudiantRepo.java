package com.dsi.absencems.repository;

import com.dsi.absencems.models.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepo extends JpaRepository<Etudiant,String> {
}

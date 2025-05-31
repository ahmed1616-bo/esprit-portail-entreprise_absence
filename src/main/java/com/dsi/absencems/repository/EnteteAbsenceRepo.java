package com.dsi.absencems.repository;

import com.dsi.absencems.models.entities.EnteteAbsence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnteteAbsenceRepo extends JpaRepository<EnteteAbsence,Integer> {
}

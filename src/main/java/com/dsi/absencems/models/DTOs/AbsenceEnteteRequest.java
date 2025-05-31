package com.dsi.absencems.models.DTOs;


import com.dsi.absencems.models.entities.Absence;
import com.dsi.absencems.models.entities.EnteteAbsence;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbsenceEnteteRequest {
    private EnteteAbsence enteteAbsence;
    private Absence absence;

}

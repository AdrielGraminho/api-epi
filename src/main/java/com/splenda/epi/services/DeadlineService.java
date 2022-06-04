package com.splenda.epi.services;

import com.splenda.epi.entities.dtos.DeadLineDTO;

import java.time.LocalDate;
import java.util.List;

public interface DeadlineService {
    List<DeadLineDTO> findDeadLineByIdBusinessUnitAndDate(Integer idBusinessUnit, LocalDate dateDeadline);
}

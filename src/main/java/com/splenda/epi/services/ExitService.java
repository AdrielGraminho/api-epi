package com.splenda.epi.services;

import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.entities.dtos.InputExitDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExitService {
    List<LocalDate> findAllDateNotReceivedByBusinessUnit(Integer idBusinessUnit);

    List<ExitItemDTO> findExitItemByBusinessUnitAndDateForecast(Integer idBusinessUnit, LocalDate date);

    List<ExitItemDTO> findExitItemDateForecast(LocalDate dateDeadLine);

    void save(InputExitDTO inputExitDTO);
}

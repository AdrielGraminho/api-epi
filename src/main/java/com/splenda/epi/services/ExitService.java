package com.splenda.epi.services;

import com.splenda.epi.entities.dtos.ExitItemDto;

import java.time.LocalDate;
import java.util.List;

public interface ExitService {
    List<LocalDate> findAllDateNotReceivedByBusinessUnit(Integer idBusinessUnit);

    List<ExitItemDto> findExitItemByBusinessUnitAndDateForecast(Integer idBusinessUnit, LocalDate date);
}

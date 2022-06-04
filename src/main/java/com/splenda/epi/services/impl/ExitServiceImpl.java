package com.splenda.epi.services.impl;

import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.repository.ExitRepository;
import com.splenda.epi.services.ExitService;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExitServiceImpl implements ExitService {

    private final ExitRepository exitRepository;

    private final PublicBusinessUnitService publicBusinessUnitService;

    public ExitServiceImpl(ExitRepository exitRepository, PublicBusinessUnitService publicBusinessUnitService) {
        this.exitRepository = exitRepository;
        this.publicBusinessUnitService = publicBusinessUnitService;
    }

    @Override
    public List<LocalDate> findAllDateNotReceivedByBusinessUnit(Integer idBusinessUnit) {

        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(idBusinessUnit));

        return exitRepository.findAllDateNotReceivedByBusinessUnit(Long.valueOf(idBusinessUnit));
    }

    @Override
    public List<ExitItemDTO> findExitItemByBusinessUnitAndDateForecast(Integer idBusinessUnit, LocalDate date) {
        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(idBusinessUnit));
        return exitRepository.findExitItemByBusinessUnitAndDateForecast(idBusinessUnit, date);
    }

    @Override
    public List<ExitItemDTO> findExitItemDateForecast(LocalDate date) {
        return exitRepository.findExitItemDateForecast(date);
    }
}

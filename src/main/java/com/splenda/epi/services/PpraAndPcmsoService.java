package com.splenda.epi.services;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.entities.dtos.PpraAndPcmsoDTO;

import java.time.LocalDate;
import java.util.List;

public interface PpraAndPcmsoService {
    List<PpraAndPcmso> findLastPpraAndPcmsoByUnitId(Integer businessUnitId);


    List<PpraAndPcmsoDTO> findByBusinessUnitAndExpirationDate(Integer idBusinessUnit, LocalDate expirationDate);

    List<PpraAndPcmsoDTO> findByExpirationDate(LocalDate expirationDate);

    List<LocalDate> findAllExpirationDateByBusinessUnit(Long idBusinessUnit);
}

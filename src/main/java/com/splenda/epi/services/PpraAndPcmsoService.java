package com.splenda.epi.services;

import com.splenda.epi.entities.core.PpraAndPcmso;

import java.util.List;

public interface PpraAndPcmsoService {
    List<PpraAndPcmso> findLastPpraAndPcmsoByUnitId(Integer businessUnitId);
}

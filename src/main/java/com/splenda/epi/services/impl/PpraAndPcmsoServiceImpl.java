package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.repository.PpraAndPcmsoRepository;
import com.splenda.epi.services.PpraAndPcmsoService;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PpraAndPcmsoServiceImpl implements PpraAndPcmsoService {

    private final PpraAndPcmsoRepository ppraAndPcmsoRepository;

    private final PublicBusinessUnitService publicBusinessUnitService;

    public PpraAndPcmsoServiceImpl(PpraAndPcmsoRepository ppraAndPcmsoRepository, PublicBusinessUnitService publicBusinessUnitService) {
        this.ppraAndPcmsoRepository = ppraAndPcmsoRepository;
        this.publicBusinessUnitService = publicBusinessUnitService;
    }

    @Override
    public List<PpraAndPcmso> findLastPpraAndPcmsoByUnitId(Integer businessUnitId) {
        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(businessUnitId));
        return ppraAndPcmsoRepository.findLastPpraAndPcmsoByUnitId(businessUnitId);
    }
}

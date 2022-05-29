package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.exceptions.BusinessUnitNotFoundException;
import com.splenda.epi.repository.PublicBusinessUnitRepository;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.springframework.stereotype.Service;

@Service
public class PublicBusinessUnitServiceImpl implements PublicBusinessUnitService {

      PublicBusinessUnitRepository publicBusinessUnitRepository;

    public PublicBusinessUnitServiceImpl(PublicBusinessUnitRepository publicBusinessUnitRepository) {
        this.publicBusinessUnitRepository = publicBusinessUnitRepository;
    }

    @Override
    public PublicBusinessUnit findPublicBusinessUnitById(Long idBusinessUnit) {
        return publicBusinessUnitRepository.findById(idBusinessUnit).orElseThrow(()-> new BusinessUnitNotFoundException("business-unit.not-found"));
    }
}

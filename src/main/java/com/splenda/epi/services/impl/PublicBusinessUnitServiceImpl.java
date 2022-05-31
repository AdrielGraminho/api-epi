package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.exceptions.BusinessUnitNotFoundException;
import com.splenda.epi.repository.PublicBusinessUnitRepository;
import com.splenda.epi.services.PublicBusinessUnitService;
import com.splenda.epi.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicBusinessUnitServiceImpl implements PublicBusinessUnitService {

      private final PublicBusinessUnitRepository publicBusinessUnitRepository;

    public PublicBusinessUnitServiceImpl(PublicBusinessUnitRepository publicBusinessUnitRepository) {
        this.publicBusinessUnitRepository = publicBusinessUnitRepository;
    }

    @Override
    public PublicBusinessUnit findPublicBusinessUnitById(Long idBusinessUnit) {
        return publicBusinessUnitRepository.findById(idBusinessUnit).orElseThrow(()-> new BusinessUnitNotFoundException("business-unit.not-found"));
    }

    @Override
    public List<PublicBusinessUnit> findByPermissionUser() {
        UserUtils userUtils = new UserUtils();
        if (userUtils.getUserDetails().getAuthorities().toString().contains("TOTAL")) {
            List<PublicBusinessUnit> publicBusinessUnitList = publicBusinessUnitRepository.findAll();
            if (publicBusinessUnitList.isEmpty()){
                throw new BusinessUnitNotFoundException("business-unit.not-found");
            }
            return publicBusinessUnitList;
        }
        else {
            List<PublicBusinessUnit> publicBusinessUnitByUserNameList = publicBusinessUnitRepository.findByUserName(userUtils.getUserDetails().getUsername());
            if (publicBusinessUnitByUserNameList.isEmpty()){
                throw new BusinessUnitNotFoundException("business-unit.not-found");
            }
            return publicBusinessUnitByUserNameList;
        }
    }
}

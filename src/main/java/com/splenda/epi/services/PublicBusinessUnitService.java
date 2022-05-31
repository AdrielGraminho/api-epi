package com.splenda.epi.services;

import com.splenda.epi.entities.core.PublicBusinessUnit;

import java.util.List;

public interface PublicBusinessUnitService {
    PublicBusinessUnit findPublicBusinessUnitById(Long idBusinessUnit);

    List<PublicBusinessUnit> findByPermissionUser();
}

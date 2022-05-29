package com.splenda.epi.services;

import com.splenda.epi.entities.core.AuditItem;
import com.splenda.epi.entities.core.AuditType;

import java.util.List;

public interface AuditService {
    List<AuditType> findAllAuditType();

    AuditItem findAuditItemById(Long id);

}

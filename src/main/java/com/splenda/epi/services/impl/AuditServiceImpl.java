package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.repository.AuditTypeRepository;
import com.splenda.epi.services.AuditService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditTypeRepository auditTypeRepository;

    public AuditServiceImpl(AuditTypeRepository auditTypeRepository) {
        this.auditTypeRepository = auditTypeRepository;
    }

    @Override
    public List<AuditType> findAllAuditType() {
        return auditTypeRepository.findAll();
    }
}

package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.AuditItem;
import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.entities.exceptions.AuditItemNotFoundException;
import com.splenda.epi.repository.AuditItemRepository;
import com.splenda.epi.repository.AuditTypeRepository;
import com.splenda.epi.services.AuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditTypeRepository auditTypeRepository;

    private final AuditItemRepository auditItemRepository;

    public AuditServiceImpl(AuditTypeRepository auditTypeRepository, AuditItemRepository auditItemRepository) {
        this.auditTypeRepository = auditTypeRepository;
        this.auditItemRepository = auditItemRepository;
    }

    @Override
    public List<AuditType> findAllAuditType() {
        return auditTypeRepository.findAll();
    }

    @Override
    public AuditItem findAuditItemById(Long id) {
        return auditItemRepository.findById(id).orElseThrow(() -> new AuditItemNotFoundException("user.not-found"));
    }
}

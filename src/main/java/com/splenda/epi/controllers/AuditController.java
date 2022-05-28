package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.services.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('TOTAL')")
    public ResponseEntity<List<AuditType>> findAllAuditType() {
        return ResponseEntity.ok(auditService.findAllAuditType());
    }
}

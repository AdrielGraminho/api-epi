package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.InputAuditedItemDTO;
import com.splenda.epi.services.AuditedItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/audited")
public class AuditedItemController {

    private final AuditedItemService auditedItemService;

    public AuditedItemController(AuditedItemService auditedItemService) {
        this.auditedItemService = auditedItemService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('TOTAL')")
    public ResponseEntity save(@RequestBody @Valid InputAuditedItemDTO inputAuditedItemDTO){
        auditedItemService.save(inputAuditedItemDTO);
        return ResponseEntity.ok().build();
    }


}

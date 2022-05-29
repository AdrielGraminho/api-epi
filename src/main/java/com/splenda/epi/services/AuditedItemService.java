package com.splenda.epi.services;

import com.splenda.epi.entities.dtos.InputAuditedItemDTO;

public interface AuditedItemService {
    void save(InputAuditedItemDTO inputAuditedItemdto);
}

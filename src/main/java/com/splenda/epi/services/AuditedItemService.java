package com.splenda.epi.services;

import com.splenda.epi.dtos.InputAuditedItemDTO;
import com.splenda.epi.entities.core.AuditItem;

public interface AuditedItemService {
    void save(InputAuditedItemDTO inputAuditedItemdto);
}

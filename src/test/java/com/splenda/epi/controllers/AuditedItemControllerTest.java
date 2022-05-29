package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.InputAuditedItemDTO;
import com.splenda.epi.services.AuditedItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditedItemControllerTest {

    @InjectMocks
    private AuditedItemController auditedItemController;

    @Mock
    private AuditedItemService auditedItemService;

    @Test
    public void shouldCallAuditedItemServiceWhenSaveAuditedItem(){
        InputAuditedItemDTO inputAuditedItemDTO = new InputAuditedItemDTO();
        doNothing().when(auditedItemService).save(inputAuditedItemDTO);

        ResponseEntity result = auditedItemController.save(inputAuditedItemDTO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(auditedItemService, times(1)).save(inputAuditedItemDTO);
    }
}

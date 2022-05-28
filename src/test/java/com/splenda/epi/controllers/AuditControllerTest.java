package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.services.AuditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditControllerTest {
    @InjectMocks
    AuditController auditController;

    @Mock
    AuditService auditService;

    @Test
    public void shouldBeReturnAllWhenFindAll(){
        List<AuditType> auditTypeList = new ArrayList<>();
        when(auditService.findAllAuditType()).thenReturn(auditTypeList);

        ResponseEntity<List<AuditType>> result = auditController.findAllAuditType();

        assertEquals(ResponseEntity.ok(auditTypeList), result);
        verify((auditService), times(1)).findAllAuditType();
    }
}

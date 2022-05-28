package com.splenda.epi.services;

import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.repository.AuditTypeRepository;
import com.splenda.epi.services.impl.AuditServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditServiceImplTest {

    @InjectMocks
    private AuditServiceImpl auditServiceImpl;

    @Mock
    private AuditTypeRepository auditTypeRepository;

    @Test
    public void shouldBeReturnAllAuditWhenFindAll(){
        List<AuditType> auditTypeList = new ArrayList<>();

        when(auditTypeRepository.findAll()).thenReturn(auditTypeList);

        List<AuditType> result = auditServiceImpl.findAllAuditType();

        assertEquals(auditTypeList, result);
        verify(auditTypeRepository, times(1)).findAll();

    }

}

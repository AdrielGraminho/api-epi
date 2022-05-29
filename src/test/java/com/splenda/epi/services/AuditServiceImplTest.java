package com.splenda.epi.services;

import com.splenda.epi.entities.core.AuditItem;
import com.splenda.epi.entities.core.AuditType;
import com.splenda.epi.entities.exceptions.AuditItemNotFoundException;
import com.splenda.epi.repository.AuditItemRepository;
import com.splenda.epi.repository.AuditTypeRepository;
import com.splenda.epi.services.impl.AuditServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditServiceImplTest {

    @InjectMocks
    private AuditServiceImpl auditServiceImpl;

    @Mock
    private AuditTypeRepository auditTypeRepository;

    @Mock
    private AuditItemRepository auditItemRepository;

    AuditItem auditItem = new AuditItem();

    @BeforeEach
    public void setUp(){
        AuditItem.builder()
                .idAuditItem(1l)
                .auditType(new AuditType())
                .description("teste")
                .build();
    }

    @Test
    public void shouldBeReturnAllAuditWhenFindAll(){
        List<AuditType> auditTypeList = new ArrayList<>();

        when(auditTypeRepository.findAll()).thenReturn(auditTypeList);

        List<AuditType> result = auditServiceImpl.findAllAuditType();

        assertEquals(auditTypeList, result);
        verify(auditTypeRepository, times(1)).findAll();

    }

    @Test
    public void shouldBeReturnExceptionWhenFindAuditItemByIdAndNotFound(){
        when(auditItemRepository.findById(anyLong())).thenReturn(Optional.empty());
        String keyException = "";
        try {
            auditServiceImpl.findAuditItemById(1l);
        } catch (AuditItemNotFoundException auditItemNotFoundException){
            keyException = auditItemNotFoundException.getKey();
        }

        assertEquals("user.not-found", keyException);
        verify(auditItemRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldBeReturnAuditItemWhenFindById(){
        when(auditItemRepository.findById(anyLong())).thenReturn(Optional.of(auditItem));
        AuditItem result = auditServiceImpl.findAuditItemById(1l);
        assertEquals(auditItem, result);
        verify(auditItemRepository, times(1)).findById(anyLong());
    }

}

package com.splenda.epi.services;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.exceptions.BusinessUnitNotFoundException;
import com.splenda.epi.repository.PublicBusinessUnitRepository;
import com.splenda.epi.services.impl.PublicBusinessUnitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicBusinessUnitServiceImplTest {
    @InjectMocks
    PublicBusinessUnitServiceImpl publicBusinessUnitService;

    @Mock
    PublicBusinessUnitRepository publicBusinessUnitRepository;

    PublicBusinessUnit publicBusinessUnit = new PublicBusinessUnit();

    @BeforeEach
    public void setUp(){
        publicBusinessUnit = PublicBusinessUnit.builder()
                .idBusinessUnit(1l)
                .code(10001)
                .codeMetadados("001")
                .build();
    }

    @Test
    public void shouldBeReturnExceptionWhenFindBusinessUnitByIdAndNotFound(){
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.empty());

        String exceptionKey = "";
        try {
            publicBusinessUnitService.findPublicBusinessUnitById(1l);
        } catch (BusinessUnitNotFoundException businessUnitNotFoundException){
            exceptionKey = businessUnitNotFoundException.getKey();
        }

        assertEquals("business-unit.not-found", exceptionKey);
    }

    @Test
    public void shouldBeReturnPublicBusinessUnitWhenFindBusinessUnitById(){
        when(publicBusinessUnitRepository.findById(anyLong())).thenReturn(Optional.of(publicBusinessUnit));

        PublicBusinessUnit result = publicBusinessUnitService.findPublicBusinessUnitById(1l);

        assertEquals(publicBusinessUnit, result);
        verify(publicBusinessUnitRepository, times(1)).findById(anyLong());
    }
}

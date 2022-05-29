package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.repository.PpraAndPcmsoRepository;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PpraAndPcmsoServiceImplTest {

    @InjectMocks
    private PpraAndPcmsoServiceImpl ppraAndPcmsoService;

    @Mock
    private PpraAndPcmsoRepository ppraAndPcmsoRepository;

    @Mock
    private PublicBusinessUnitService publicBusinessUnitService;

    @Test
    public void shouldReturnPpraAndPcmsoWhenFindByBusinessUnit(){
        PpraAndPcmso ppraAndPcmso = PpraAndPcmso.builder().idPpraPcmso(1l).build();
        when(publicBusinessUnitService.findPublicBusinessUnitById(any())).thenReturn(new PublicBusinessUnit());
        when(ppraAndPcmsoRepository.findLastPpraAndPcmsoByUnitId(any())).thenReturn(List.of(ppraAndPcmso));

        List<PpraAndPcmso> result = ppraAndPcmsoService.findLastPpraAndPcmsoByUnitId(1);

        assertEquals(List.of(ppraAndPcmso), result);
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(any());
        verify(ppraAndPcmsoRepository, times(1)).findLastPpraAndPcmsoByUnitId(any());
    }

}

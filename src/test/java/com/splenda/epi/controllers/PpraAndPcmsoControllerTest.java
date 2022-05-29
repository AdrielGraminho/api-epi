package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.services.impl.PpraAndPcmsoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PpraAndPcmsoControllerTest {

    @InjectMocks
    private PpraAndPcmsoController ppraAndPcmsoController;

    @Mock
    private PpraAndPcmsoServiceImpl ppraAndPcmsoService;

    @Test
    public void shouldBeReturnPpraAndPcmsoWhenFindByBusinessUnitId(){
        PpraAndPcmso ppraAndPcmso = PpraAndPcmso.builder().idPpraPcmso(1l).build();
        when(ppraAndPcmsoService.findLastPpraAndPcmsoByUnitId(any())).thenReturn(List.of(ppraAndPcmso));

        ResponseEntity<List<PpraAndPcmso>> result = ppraAndPcmsoController.findLastPpraAndPcmsoByUnitId(1);

        assertEquals(ResponseEntity.ok(List.of(ppraAndPcmso)), result);
        verify(ppraAndPcmsoService, times(1)).findLastPpraAndPcmsoByUnitId(any());

    }

}

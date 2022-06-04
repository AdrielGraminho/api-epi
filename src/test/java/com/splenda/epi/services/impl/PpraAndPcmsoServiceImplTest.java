package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.dtos.PpraAndPcmsoDTO;
import com.splenda.epi.repository.PpraAndPcmsoRepository;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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

    PpraAndPcmsoDTO ppraAndPcmsoDTO = new PpraAndPcmsoDTO() {
        @Override
        public Integer getIdPpraAndPcmso() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "Test";
        }

        @Override
        public String getBusinessUnit() {
            return "HONDA";
        }
    };

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

    @Test
    public void shouldReturnPpraAndPcmsoDTOWhenFindByBusinessUnitAndExpirationDate(){

        when(ppraAndPcmsoRepository.findByBusinessUnitAndExpirationDate(any(), any())).thenReturn(List.of(ppraAndPcmsoDTO));
        List<PpraAndPcmsoDTO> result = ppraAndPcmsoService.findByBusinessUnitAndExpirationDate(1, LocalDate.now());

        assertEquals(ppraAndPcmsoDTO, result.get(0));
        verify(ppraAndPcmsoRepository, times(1)).findByBusinessUnitAndExpirationDate(any(), any());
    }

    @Test
    public void shouldReturnPpraAndPcmsoDtoListWhenFindByDate(){
        when(ppraAndPcmsoRepository.findByExpirationDate(any())).thenReturn(List.of(ppraAndPcmsoDTO));

        List<PpraAndPcmsoDTO> result = ppraAndPcmsoService.findByExpirationDate(LocalDate.now());

        assertEquals(ppraAndPcmsoDTO, result.get(0));
        verify(ppraAndPcmsoRepository, times(1)).findByExpirationDate(any());

    }

    @Test
    public void shouldBeReturnAllDatesWhenFindDateByPpraAndPcmso(){
        when(ppraAndPcmsoRepository.findAllExpirationDateByBusinessUnit(any())).thenReturn(List.of(LocalDate.now()));

        List<LocalDate> dates = ppraAndPcmsoService.findAllExpirationDateByBusinessUnit(1L);

        assertEquals(LocalDate.now(), dates.get(0));
        verify(ppraAndPcmsoRepository, times(1)).findAllExpirationDateByBusinessUnit(any());
    }
}

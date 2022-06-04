package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.repository.ExitRepository;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExitServiceImplTest {

    @InjectMocks
    private ExitServiceImpl exitService;

    @Mock
    private ExitRepository exitRepository;

    @Mock
    private PublicBusinessUnitService publicBusinessUnitService;

    private ExitItemDTO exitItemDto;

    @Before
    public void setUp(){
        exitItemDto = new ExitItemDTO() {
            @Override
            public Integer getIdExit() {
                return 1;
            }

            @Override
            public String getItem() {
                return "Test";
            }

            @Override
            public String getBusinessUnit() {
                return "HONDA";
            }
        };
    }

    @Test
    public void shouldReturnAllExitByBusinessUnit(){
        LocalDate date = LocalDate.now();

        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());
        when(exitRepository.findAllDateNotReceivedByBusinessUnit(anyLong())).thenReturn(List.of(date));

        List<LocalDate> result = exitService.findAllDateNotReceivedByBusinessUnit(1);

        assertEquals(date, result.get(0));
        verify(exitRepository, times(1)).findAllDateNotReceivedByBusinessUnit(anyLong());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
    }

    @Test
    public void shouldReturnExitItemDtoWhenFindItemsByDateAndBusinessUnit(){
        List<ExitItemDTO> exitItemDtoList = new ArrayList<>();
        exitItemDtoList.add(exitItemDto);

        when(exitRepository.findExitItemByBusinessUnitAndDateForecast(any(), any())).thenReturn(exitItemDtoList);
        when(publicBusinessUnitService.findPublicBusinessUnitById(anyLong())).thenReturn(new PublicBusinessUnit());

        List<ExitItemDTO> result = exitService.findExitItemByBusinessUnitAndDateForecast(1, LocalDate.now());

        assertEquals(exitItemDtoList, result);
        verify(exitRepository, times(1)).findExitItemByBusinessUnitAndDateForecast(any(), any());
        verify(publicBusinessUnitService, times(1)).findPublicBusinessUnitById(anyLong());
    }


}

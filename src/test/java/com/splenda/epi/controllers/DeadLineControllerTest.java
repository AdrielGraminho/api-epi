package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.DeadLineDTO;
import com.splenda.epi.services.DeadlineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeadLineControllerTest {

    @InjectMocks
    DeadLineController deadLineController;

    @Mock
    DeadlineService deadlineService;

    @Test
    public void shouldCallDeadLineServiceWhenFindByBusinessUnitAndDate(){
        when(deadlineService.findDeadLineByIdBusinessUnitAndDate(any(), any())).thenReturn(List.of());

        ResponseEntity<List<DeadLineDTO>> result = deadLineController.findDeadLineByIdBusinessUnitAndDate(1, LocalDate.now());

        assertEquals(ResponseEntity.ok(List.of()), result);
        verify(deadlineService, times(1)).findDeadLineByIdBusinessUnitAndDate(any(), any());
    }

    @Test
    public void shouldReturnLocalDateListWhenFindExpirationDateByPermission(){
        when(deadlineService.findAllDateByUserPermission()).thenReturn(List.of(LocalDate.now()));

        ResponseEntity<List<LocalDate>> result = deadLineController.findAllDateByUserPermission();

        assertEquals(ResponseEntity.ok(List.of(LocalDate.now())), result);
        verify(deadlineService, times(1)).findAllDateByUserPermission();
    }

}

package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.InputExitDTO;
import com.splenda.epi.services.ExitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExitControllerTest {

    @InjectMocks
    ExitController exitController;

    @Mock
    ExitService exitService;

    @Test
    public void shouldCallSaveService(){
        InputExitDTO inputExitDTO = new InputExitDTO();
        doNothing().when(exitService).save(any());

        ResponseEntity result = exitController.save(inputExitDTO);

        assertEquals(ResponseEntity.ok().build(), result);
        verify(exitService, times(1)).save(any());

    }

}

package com.splenda.epi.controllers;

import com.splenda.epi.services.ReceivedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceivedControllerTest {
    @InjectMocks
    private ReceivedController receivedController;

    @Mock
    private ReceivedService receivedService;

    @Test
    public void shouldCallServiceSave(){
        doNothing().when(receivedService).save(anyInt());

        ResponseEntity result = receivedController.save(1);

        assertEquals(ResponseEntity.ok().build(), result);
        verify(receivedService, times(1)).save(anyInt());
    }
}

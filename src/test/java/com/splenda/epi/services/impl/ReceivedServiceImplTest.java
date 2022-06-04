package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Exit;
import com.splenda.epi.entities.core.Received;
import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.exceptions.ReceivedAlreadyExistException;
import com.splenda.epi.repository.ReceivedRepository;
import com.splenda.epi.services.ExitService;
import com.splenda.epi.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceivedServiceImplTest {

    @InjectMocks
    private ReceivedServiceImpl receivedService;

    @Mock
    private ReceivedRepository receivedRepository;

    @Mock
    private UserService userService;

    @Mock
    private ExitService exitService;

    @Test
    public void shouldSaveByIdExit(){
        when(receivedRepository.findByExit(anyLong())).thenReturn(Optional.empty());
        when(userService.userLogged()).thenReturn(User.builder().idUser(1L).build());
        when(exitService.findById(anyLong())).thenReturn(Exit.builder().idExit(2L).build());
        when(receivedRepository.save(any())).thenReturn(new Received());

        receivedService.save(1);

        verify(userService, times(1)).userLogged();
        verify(exitService, times(1)).findById(anyLong());
        verify(receivedRepository, times(1)).save(any());
    }

    @Test
    public void shouldReturnExceptionWhenFindByExitAndExists(){
        when(receivedRepository.findByExit(anyLong())).thenReturn(Optional.of(new Received()));

        String keyException = "";
        try {
            receivedService.save(1);
        }catch (ReceivedAlreadyExistException receivedAlreadyExistException){
            keyException = receivedAlreadyExistException.getKey();
        }

        assertEquals("received.already-exists", keyException);
        verify(receivedRepository, times(1)).findByExit(anyLong());
    }

}

package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.exceptions.UserNotFoundException;
import com.splenda.epi.repository.UserRepository;
import com.splenda.epi.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldBeReturnUserWhenFindByUserId(){
        User user = User.builder().idUser(1l).build();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        User result = userService.findUserById(1l);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(any());
    }

    @Test
    public void shouldBeReturnExceptionWhenFindByUserIdAndNotFound(){
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        String keyException = "";
        try {
            userService.findUserById(1l);
        }catch (UserNotFoundException userNotFoundException){
            keyException = userNotFoundException.getKey();
        }
        assertEquals("user.not-found", keyException);
    }

    @Test
    public void shouldBeReturnUserWhenFindByUserName(){
        User user = User.builder().idUser(1l).build();
        when(userRepository.findByName(any())).thenReturn(Optional.of(user));

        User result = userService.findByUserName("teste");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByName(any());
    }

    @Test
    public void shouldBeReturnExceptionWhenFindByUserNameAndNotFound(){
        when(userRepository.findByName(any())).thenReturn(Optional.empty());

        String keyException = "";
        try {
            userService.findByUserName("");
        }catch (UserNotFoundException userNotFoundException){
            keyException = userNotFoundException.getKey();
        }
        assertEquals("user.not-found", keyException);
    }

}

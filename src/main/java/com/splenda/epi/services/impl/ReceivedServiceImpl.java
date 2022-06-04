package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Exit;
import com.splenda.epi.entities.core.Received;
import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.exceptions.ReceivedAlreadyExistException;
import com.splenda.epi.repository.ReceivedRepository;
import com.splenda.epi.services.ExitService;
import com.splenda.epi.services.ReceivedService;
import com.splenda.epi.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReceivedServiceImpl implements ReceivedService {

    private final ReceivedRepository receivedRepository;
    private final UserService userService;

    private final ExitService exitService;

    public ReceivedServiceImpl(ReceivedRepository receivedRepository, UserService userService, ExitService exitService) {
        this.receivedRepository = receivedRepository;
        this.userService = userService;
        this.exitService = exitService;
    }

    @Override
    public void save(Integer idExit) {
        validIfExists(idExit);
        Received receivedToSave = buildReceived(idExit);
        receivedRepository.save(receivedToSave);
    }

    private void validIfExists(Integer idExit) {
        Optional<Received> received = receivedRepository.findByExit(Long.valueOf(idExit));
        if (received.isPresent()){
            throw new ReceivedAlreadyExistException("received.already-exists");
        }
    }

    private Received buildReceived(Integer idExit) {
        Exit exit = exitService.findById(Long.valueOf(idExit));
        User user = userService.userLogged();

        return Received.builder()
                .dateReceived(LocalDate.now())
                .exit(exit)
                .user(user)
                .build();
    }
}

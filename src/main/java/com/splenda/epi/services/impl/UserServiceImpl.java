package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.exceptions.UserNotFoundException;
import com.splenda.epi.repository.UserRepository;
import com.splenda.epi.services.UserService;
import com.splenda.epi.utils.UserUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user.not-found"));
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("user.not-found"));
    }

    @Override
    public UserDetails getUserDetailsLogged() {
        UserUtils userUtils = new UserUtils();
        return userUtils.getUserDetails();
    }
}

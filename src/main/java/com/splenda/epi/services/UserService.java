package com.splenda.epi.services;

import com.splenda.epi.entities.core.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    User findUserById(Long userId);

    User findByUserName(String userName);

    UserDetails getUserDetailsLogged();

    User userLogged();
}

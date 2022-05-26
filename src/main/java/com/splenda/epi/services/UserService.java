package com.splenda.epi.services;

import com.splenda.epi.entities.core.User;

public interface UserService {
    User findUserById(Long userId);
}

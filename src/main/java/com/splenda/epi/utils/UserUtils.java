package com.splenda.epi.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {
    public UserDetails getUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (UserDetails) principal;
    }
}

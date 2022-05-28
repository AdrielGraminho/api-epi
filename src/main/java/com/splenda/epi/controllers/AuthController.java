package com.splenda.epi.controllers;

import javax.validation.Valid;

import com.splenda.epi.security.payload.request.LoginRequest;
import com.splenda.epi.security.payload.response.JwtResponse;
import com.splenda.epi.repository.UserRepository;
import com.splenda.epi.security.jwt.JwtUtils;
import com.splenda.epi.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        int expToken = jwtUtils.getJwtExpirationMs();

        return ResponseEntity.ok(new JwtResponse(jwt, expToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getNameRole()));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('TOTAL')")
    public ResponseEntity<String> teste () {
        return ResponseEntity.ok("success");
    }

}
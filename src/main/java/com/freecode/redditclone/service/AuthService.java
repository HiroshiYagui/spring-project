package com.freecode.redditclone.service;

import java.time.Instant;

import com.freecode.redditclone.dto.RegisterRequest;
import com.freecode.redditclone.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void signup(RegisterRequest registerRequest){
        User user =new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()) );
        user.setCreated(Instant.now());
        user.setEnabled(false);
    }
}

package com.freecode.redditclone.service;

import java.time.Instant;
import java.util.UUID;

import com.freecode.redditclone.dto.RegisterRequest;
import com.freecode.redditclone.model.User;
import com.freecode.redditclone.model.VerificationToken;
import com.freecode.redditclone.repository.UserRepository;
import com.freecode.redditclone.repository.VerificationTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public void signup(RegisterRequest registerRequest){
        User user =new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()) );
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token=generateVerificationToken(user);
    }


    private String generateVerificationToken(User user){
        String token=UUID.randomUUID().toString();
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}

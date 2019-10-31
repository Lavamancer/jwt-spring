package com.example.jwt.service;

import com.example.jwt.domain.Token;
import com.example.jwt.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TokenService {

    @Autowired TokenRepository tokenRepository;
    @Autowired UserService userService;


    public Token create(String username) {
        String accessToken = UUID.randomUUID().toString();
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setUser(userService.findByUsername(username));
        return tokenRepository.save(token);
    }

    public boolean existsByAccessToken(String accessToken) {
        return tokenRepository.existsByAccessToken(accessToken);
    }
}

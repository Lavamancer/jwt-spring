package com.example.jwt.service;

import com.example.jwt.domain.Token;
import com.example.jwt.repository.TokenRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TokenService {

    private static final int TOKEN_VALIDITY_SECONDS = 15;

    @Autowired TokenRepository tokenRepository;
    @Autowired UserService userService;


    public Token create(String username) {
        String accessToken = UUID.randomUUID().toString();
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setUser(userService.findByUsername(username));
        token.setDate(DateTime.now());
        return tokenRepository.save(token);
    }

    public boolean existsByAccessTokenAndCheckDate(String accessToken) {
        if (!tokenRepository.existsByAccessToken(accessToken)) {
            return false;
        }
        Token token = tokenRepository.findByAccessToken(accessToken);
        if (token.getDate() == null || DateTime.now().isAfter(token.getDate().plusSeconds(TOKEN_VALIDITY_SECONDS))) {
            tokenRepository.delete(token);
            return false;
        }
        return true;
    }

    public boolean existsByUsernameAndCheckDate(String username) {
        if (!tokenRepository.existsByUser_Username(username)) {
            return false;
        }
        Token token = tokenRepository.findByUser_Username(username);
        if (token.getDate() == null || DateTime.now().isAfter(token.getDate().plusSeconds(TOKEN_VALIDITY_SECONDS))) {
            tokenRepository.delete(token);
            return false;
        }
        return tokenRepository.existsByUser_Username(username);
    }

    public Token findByUsername(String username) {
        return tokenRepository.findByUser_Username(username);
    }
}

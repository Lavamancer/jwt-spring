package com.example.jwt.web.rest;

import com.example.jwt.domain.Token;
import com.example.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRest {

    @Autowired TokenService tokenService;

    @PostMapping("/public/login")
    public ResponseEntity<Token> postLogin(@RequestParam String username, @RequestParam String password) {
        return null;
    }

}

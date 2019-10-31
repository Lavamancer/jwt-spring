package com.example.jwt.service;

import com.example.jwt.domain.Token;
import com.example.jwt.domain.User;
import com.example.jwt.repository.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired UserRepository userRepository;
    @Autowired TokenService tokenService;


    public User register(User user) {
        return userRepository.save(user);
    }

    public Token login(String username, String password) {
        if (!userRepository.existsByUsernameAndPassword(username, password)) {
            throw new RuntimeException();
        }

        if (tokenService.existsByUsernameAndCheckDate(username)) {
            Token token = tokenService.findByUsername(username);
            token.setDate(DateTime.now());
            return token;
        } else {
            return tokenService.create(username);
        }
    }

    public User findByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new RuntimeException();
        }
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}

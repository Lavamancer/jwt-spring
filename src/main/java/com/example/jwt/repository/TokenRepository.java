package com.example.jwt.repository;

import com.example.jwt.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    boolean existsByAccessToken(String accessToken);

    Token findByAccessToken(String accessToken);

    boolean existsByUser_Username(String username);

    Token findByUser_Username(String username);

}

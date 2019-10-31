package com.example.jwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Token {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken; // 12h

//    private String refreshToken; // 30d

    @JsonIgnore
    @OneToOne
    private User user;

}

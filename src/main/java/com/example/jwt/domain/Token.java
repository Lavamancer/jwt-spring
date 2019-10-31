package com.example.jwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.joda.time.DateTime;

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

    private DateTime date;

    @JsonIgnore
    @OneToOne
    private User user;

}

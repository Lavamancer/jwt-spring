package com.example.jwt.security;

import com.example.jwt.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Configuration
public class SecurityAspect {

    private static Pattern bearerRegex = Pattern.compile("Bearer (?<token>[a-zA-Z0-9-]+)");

    @Autowired TokenService tokenService;


    @Before("(@within(com.example.jwt.security.Security) || @annotation(com.example.jwt.security.Security)) && !@annotation(com.example.jwt.security.SecurityIgnore)")
    public void securityBefore(JoinPoint joinPoint) {
        System.out.println("HELLO ASPECT");

        String token = getTokenFromRequest();

        if (!tokenService.existsByAccessTokenAndCheckDate(token)) {
            throw new RuntimeException();
        }
    }

    private String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorization);
        if (authorization == null) {
            throw new RuntimeException();
        }


        Matcher matcher = bearerRegex.matcher(authorization);
        if (!matcher.matches()) {
            throw new RuntimeException();
        }
        return matcher.group("token");
    }

}

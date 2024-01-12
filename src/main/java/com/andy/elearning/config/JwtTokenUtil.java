package com.andy.elearning.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtTokenUtil implements Serializable {
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiration.time}")
    private int jwtExpirationMs;
}

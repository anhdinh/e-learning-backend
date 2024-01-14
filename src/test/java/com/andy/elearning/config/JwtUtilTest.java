package com.andy.elearning.config;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Log4j2
public class JwtUtilTest {
    @Autowired
    JwtUtils jwtUtils;

    /* setting up test */
    private String username = "dta89uct@gmail.com";
    private String adminRole = "ROLE_ADMIN";
    private Map<String, Object> claims = new HashMap<>();

    @BeforeEach
    public void prepare() {
        List<String> listRoles = new ArrayList<>();
        listRoles.add(adminRole);
        claims.put(JwtUtils.ROLES, listRoles);
    }
    /* ending setup test */


    @Test
    public void generateJwtToken_ReturnJWTTokenAndValid() {
        String token = jwtUtils.generateToken(claims, username);
        Assertions.assertTrue(jwtUtils.isTokenValidate(token, username));
    }

    @Test
    public void extractUsernameFromJwtToken_ReturnUsername() {
        String token = jwtUtils.generateToken(claims, username);
        log.info("token :{}",token);
        String username = jwtUtils.extractUsername(token);
        Assertions.assertTrue(jwtUtils.isTokenValidate(token, username));
        Assertions.assertTrue(this.username.equals(username));
    }
}

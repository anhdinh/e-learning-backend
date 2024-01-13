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
    JwtTokenUtil jwtTokenUtil;

    /* setting up test */
    private String username = "dta89uct@gmail.com";
    private String adminRole = "ROLE_ADMIN";
    private Map<String, Object> claims = new HashMap<>();

    @BeforeEach
    public void prepare() {
        List<String> listRoles = new ArrayList<>();
        listRoles.add(adminRole);
        claims.put(JwtTokenUtil.ROLES, listRoles);
    }
    /* ending setup test */


    @Test
    public void generateJwtToken_ReturnJWTTokenAndValid() {
        String token = jwtTokenUtil.generateToken(claims, username);
        Assertions.assertTrue(jwtTokenUtil.isTokenValidate(token, username));
    }

    @Test
    public void extractUsernameFromJwtToken_ReturnUsername() {
        String token = jwtTokenUtil.generateToken(claims, username);
        log.info("token :{}",token);
        String username = jwtTokenUtil.extractUsername(token);
        Assertions.assertTrue(jwtTokenUtil.isTokenValidate(token, username));
        Assertions.assertTrue(this.username.equals(username));
    }
}

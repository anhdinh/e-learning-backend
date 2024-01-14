package com.andy.elearning.service;

import com.andy.elearning.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserDetailsService userService;

    @Test
    @Transactional
    public void Test_GetUserByUsername_ReturnAnUser(){
        User user = (User)  userService.loadUserByUsername("dta89uct@gmail.com");
        System.out.println(user.getUsername());
        user.getAuthorities().stream().forEach(item->{
            System.out.println(item.getAuthority());
        });
    }

}

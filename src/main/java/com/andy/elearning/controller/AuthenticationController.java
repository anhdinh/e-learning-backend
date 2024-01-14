package com.andy.elearning.controller;

import com.andy.elearning.dto.payload.response.UserLoginResponse;
import com.andy.elearning.dto.UserDto;
import com.andy.elearning.dto.payload.request.UserLoginRequest;
import com.andy.elearning.dto.payload.request.UserRegisterRequest;
import com.andy.elearning.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        UserDto userDto = authenticationService.register(userRegisterRequest);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse  = authenticationService.login(userLoginRequest);
        return ResponseEntity.ok(userLoginResponse);
    }
}

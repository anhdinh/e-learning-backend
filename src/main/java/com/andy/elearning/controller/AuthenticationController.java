package com.andy.elearning.controller;

import com.andy.elearning.dto.payload.response.RefreshTokenResponse;
import com.andy.elearning.dto.payload.response.UserLoginResponse;
import com.andy.elearning.dto.UserDto;
import com.andy.elearning.dto.payload.request.UserLoginRequest;
import com.andy.elearning.dto.payload.request.UserRegisterRequest;
import com.andy.elearning.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        UserLoginResponse userLoginResponse = authenticationService.login(userLoginRequest);
        return ResponseEntity.ok(userLoginResponse);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam(name = "token") Optional<String> refreshTokenOp) {
        String refreshToken = refreshTokenOp.orElseGet(() -> "not provided");
        RefreshTokenResponse response = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }
}

package com.andy.elearning.service;

import com.andy.elearning.dto.payload.request.UserLoginRequest;
import com.andy.elearning.dto.payload.request.UserRegisterRequest;
import com.andy.elearning.dto.payload.response.UserLoginResponse;
import com.andy.elearning.dto.UserDto;

public interface AuthenticationService {
    public UserDto register(UserRegisterRequest registerRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}

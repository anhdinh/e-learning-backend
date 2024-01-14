package com.andy.elearning.dto.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserLoginResponse {
    private String jwtToken;
    private String refreshToken;
    private String username;
    private Set<String> roles;
}

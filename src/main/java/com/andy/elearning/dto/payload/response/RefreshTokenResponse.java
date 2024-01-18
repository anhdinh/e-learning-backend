package com.andy.elearning.dto.payload.response;

import lombok.Data;

@Data
public class RefreshTokenResponse {
    private String jwtToken;
    private String refreshToken;
}

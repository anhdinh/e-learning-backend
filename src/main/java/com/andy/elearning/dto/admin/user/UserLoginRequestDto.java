package com.andy.elearning.dto.admin.user;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String username;
    private String password;
}

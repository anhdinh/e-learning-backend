package com.andy.elearning.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest extends Request {
    @NotBlank
    @Size(max = 50)
    @Email
    public String username;

    @NotBlank
    @Size(min = 6, max = 40)
    public String password;
}

package com.andy.elearning.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    public String name;

    @NotBlank
    @Size(max = 50)
    @Email
    public String username;

    @NotBlank
    @Size(min = 6, max = 40)
    public String password;
}

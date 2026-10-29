package com.apibridge.integration.integration.user.dto;

import com.apibridge.integration.integration.common.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @Email(message = "Enter Valid email")
    @NotBlank(message = "Email is mandatory")
    @Lowercase(message = "Email must be in lowercase")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 25 ,message = "Password must be 6 to 25 characters long")
    private String password;

    private boolean active;
}

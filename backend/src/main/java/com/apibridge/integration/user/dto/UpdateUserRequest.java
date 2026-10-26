package com.apibridge.integration.user.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private Boolean active;
}

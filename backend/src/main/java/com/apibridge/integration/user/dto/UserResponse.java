package com.apibridge.integration.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private List<String> roles;
    private boolean active;
}

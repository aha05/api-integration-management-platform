package com.apibridge.integration.integration.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private String description;
    private List<String> permissions;
}

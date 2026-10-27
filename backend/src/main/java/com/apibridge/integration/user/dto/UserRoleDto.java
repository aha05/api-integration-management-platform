package com.apibridge.integration.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDto {
    private Long userId;
    private Long roleId;
}

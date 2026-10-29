package com.apibridge.integration.integration.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePermissionDto {
    private Long roleId;
    private Long permissionId;
}

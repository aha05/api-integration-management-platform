package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.PermissionDto;
import com.apibridge.integration.user.entity.Permission;

public interface PermissionMapper {
    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto request);
    void update(PermissionDto request, Permission permission);
}

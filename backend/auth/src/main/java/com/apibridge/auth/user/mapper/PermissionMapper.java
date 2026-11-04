package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.PermissionDto;
import com.apibridge.auth.user.entity.Permission;

public interface PermissionMapper {
    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto request);
    void update(PermissionDto request, Permission permission);
}

package com.apibridge.integration.integration.user.mapper;

import com.apibridge.integration.integration.user.dto.RolePermissionDto;
import com.apibridge.integration.integration.user.entity.Permission;
import com.apibridge.integration.integration.user.entity.Role;
import com.apibridge.integration.integration.user.entity.RolePermission;

public interface RolePermissionMapper {
    RolePermissionDto toDto(RolePermission rolePermission);
    RolePermission toEntity(Role role, Permission permission);
}

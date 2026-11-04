package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.RolePermissionDto;
import com.apibridge.auth.user.entity.Permission;
import com.apibridge.auth.user.entity.Role;
import com.apibridge.auth.user.entity.RolePermission;

public interface RolePermissionMapper {
    RolePermissionDto toDto(RolePermission rolePermission);
    RolePermission toEntity(Role role, Permission permission);
}

package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.RolePermissionDto;
import com.apibridge.auth.user.entity.Permission;
import com.apibridge.auth.user.entity.Role;
import com.apibridge.auth.user.entity.RolePermission;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMapperImpl implements RolePermissionMapper{
    @Override
    public RolePermissionDto toDto(RolePermission rolePermission) {
        return RolePermissionDto.builder()
                .roleId(rolePermission.getRole().getId())
                .permissionId(rolePermission.getPermission().getId())
                .build();
    }

    @Override
    public RolePermission toEntity(Role role, Permission permission) {
       RolePermission rolePermission = new RolePermission();
       rolePermission.setRole(role);
       rolePermission.setPermission(permission);
       return rolePermission;
    }
}

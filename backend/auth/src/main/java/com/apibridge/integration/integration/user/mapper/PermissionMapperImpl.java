package com.apibridge.integration.integration.user.mapper;

import com.apibridge.integration.integration.user.dto.PermissionDto;
import com.apibridge.integration.integration.user.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapperImpl implements PermissionMapper {
    @Override
    public PermissionDto toDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }

    @Override
    public Permission toEntity(PermissionDto request) {
        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        return permission;
    }

    @Override
    public void update(PermissionDto request, Permission permission) {
        if (request.getName() != null) {
            permission.setName(request.getName());
        }

        if (request.getDescription() != null) {
            permission.setDescription(request.getDescription());
        }
    }
}

package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper{

    @Override
    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }

    @Override
    public Role toEntity(RoleDto request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return role;
    }

    @Override
    public void update(RoleDto request, Role role) {
        if (request.getName() != null) {
            role.setName(request.getName());
        }

        if (request.getDescription() != null) {
            role.setDescription(request.getDescription());
        }
    }
}

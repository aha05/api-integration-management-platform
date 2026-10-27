package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.entity.Role;

public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto request);
    void update(RoleDto request, Role role);
}

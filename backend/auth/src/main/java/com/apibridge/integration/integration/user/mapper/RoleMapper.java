package com.apibridge.integration.integration.user.mapper;

import com.apibridge.integration.integration.user.dto.RoleDto;
import com.apibridge.integration.integration.user.entity.Role;

public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto request);
    void update(RoleDto request, Role role);
}

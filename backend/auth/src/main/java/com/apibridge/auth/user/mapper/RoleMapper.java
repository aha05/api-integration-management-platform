package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.RoleDto;
import com.apibridge.auth.user.entity.Role;

public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto request);
    void update(RoleDto request, Role role);
}

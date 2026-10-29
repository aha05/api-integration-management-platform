package com.apibridge.integration.integration.user.mapper;

import com.apibridge.integration.integration.user.dto.UserRoleDto;
import com.apibridge.integration.integration.user.entity.Role;
import com.apibridge.integration.integration.user.entity.User;
import com.apibridge.integration.integration.user.entity.UserRole;

public interface UserRoleMapper {
    UserRoleDto toDto(UserRole userRole);
    UserRole toEntity(User user, Role role);
}

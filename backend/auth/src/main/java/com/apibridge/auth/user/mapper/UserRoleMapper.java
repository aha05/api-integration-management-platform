package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.UserRoleDto;
import com.apibridge.auth.user.entity.Role;
import com.apibridge.auth.user.entity.User;
import com.apibridge.auth.user.entity.UserRole;

public interface UserRoleMapper {
    UserRoleDto toDto(UserRole userRole);
    UserRole toEntity(User user, Role role);
}

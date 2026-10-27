package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.UserRoleDto;
import com.apibridge.integration.user.entity.*;

public interface UserRoleMapper {
    UserRoleDto toDto(UserRole userRole);
    UserRole toEntity(User user, Role role);
}

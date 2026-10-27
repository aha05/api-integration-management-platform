package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.UserRoleDto;
import com.apibridge.integration.user.entity.Role;
import com.apibridge.integration.user.entity.User;
import com.apibridge.integration.user.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapperImpl implements UserRoleMapper{
    @Override
    public UserRoleDto toDto(UserRole userRole) {
        return UserRoleDto.builder()
                .userId(userRole.getUser().getId())
                .roleId(userRole.getRole().getId())
                .build();
    }

    @Override
    public UserRole toEntity(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        return null;
    }
}

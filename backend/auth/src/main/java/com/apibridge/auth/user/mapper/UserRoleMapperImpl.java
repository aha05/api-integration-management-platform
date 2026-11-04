package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.UserRoleDto;
import com.apibridge.auth.user.entity.Role;
import com.apibridge.auth.user.entity.User;
import com.apibridge.auth.user.entity.UserRole;
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

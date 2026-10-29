package com.apibridge.integration.integration.user.mapper;

import com.apibridge.integration.integration.user.dto.UpdateUserRequest;
import com.apibridge.integration.integration.user.dto.UserRequest;
import com.apibridge.integration.integration.user.dto.UserResponse;
import com.apibridge.integration.integration.user.entity.User;

public interface UserMapper {
    UserResponse toDto(User user);
    User toEntity(UserRequest request);
    void update(UpdateUserRequest request, User user);
}

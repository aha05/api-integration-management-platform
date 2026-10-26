package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.UpdateUserRequest;
import com.apibridge.integration.user.dto.UserRequest;
import com.apibridge.integration.user.dto.UserResponse;
import com.apibridge.integration.user.entity.User;

public interface UserMapper {
    UserResponse toDto(User user);
    User toEntity(UserRequest request);
    void update(UpdateUserRequest request, User user);
}

package com.apibridge.auth.user.mapper;

import com.apibridge.auth.user.dto.UpdateUserRequest;
import com.apibridge.auth.user.dto.UserRequest;
import com.apibridge.auth.user.dto.UserResponse;
import com.apibridge.auth.user.entity.User;

public interface UserMapper {
    UserResponse toDto(User user);
    User toEntity(UserRequest request);
    void update(UpdateUserRequest request, User user);
}

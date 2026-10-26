package com.apibridge.integration.user.mapper;

import com.apibridge.integration.user.dto.UpdateUserRequest;
import com.apibridge.integration.user.dto.UserRequest;
import com.apibridge.integration.user.dto.UserResponse;
import com.apibridge.integration.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.isActive())
                .build();
    }

    @Override
    public User toEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setActive(request.isActive());
        return  user;
    }

    @Override
    public void update(UpdateUserRequest request, User user) {
        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getActive() != null) {
            user.setActive(request.getActive());
        }
    }
}

package com.apibridge.integration.user.service;

import com.apibridge.integration.common.ResourceAlreadyExistException;
import com.apibridge.integration.common.ResourceNotFoundException;
import com.apibridge.integration.common.UnauthorizeAccessException;
import com.apibridge.integration.user.dto.ChangePasswordRequest;
import com.apibridge.integration.user.dto.UpdateUserRequest;
import com.apibridge.integration.user.dto.UserRequest;
import com.apibridge.integration.user.dto.UserResponse;
import com.apibridge.integration.user.entity.UserRole;
import com.apibridge.integration.user.entity.UserRoleId;
import com.apibridge.integration.user.mapper.UserMapper;
import com.apibridge.integration.user.repository.RoleRepository;
import com.apibridge.integration.user.repository.UserRepository;
import com.apibridge.integration.user.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers (){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponse getUserById(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );

        return userMapper.toDto(user);
    }

    public UserResponse registerUser(UserRequest request) {
       if(userRepository.existsByEmail(request.getEmail())){
           throw new ResourceAlreadyExistException("User", "email", request.getEmail());
       }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserResponse update(Long userId, UpdateUserRequest request){
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );

        userMapper.update(request, user);
        var result = userRepository.save(user);
        return userMapper.toDto(result);
    }

    public void delete(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );
        userRepository.delete(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new UnauthorizeAccessException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    @Transactional
    public void assignRole(Long userId, Long roleId) {

        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );

        var role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );

        UserRoleId id = new UserRoleId(userId, roleId);

        if (userRoleRepository.existsById(id)) {
            throw new ResourceAlreadyExistException(
                    "UserRole", "id", id
            );
        }

        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);
    }

    @Transactional
    public void detachRole(Long userId, Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);

        UserRole userRole = userRoleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("UserRole", "id", id)
        );

        userRoleRepository.delete(userRole);
    }
}

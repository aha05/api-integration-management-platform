package com.apibridge.auth.user.service;

import com.apibridge.auth.common.ResourceNotFoundException;
import com.apibridge.auth.user.dto.PermissionDto;
import com.apibridge.auth.user.entity.Permission;
import com.apibridge.auth.user.entity.RolePermission;
import com.apibridge.auth.user.entity.UserRole;
import com.apibridge.auth.user.mapper.PermissionMapper;
import com.apibridge.auth.user.repository.PermissionRepository;
import com.apibridge.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public Set<String> getUserPermissions(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getRoles().stream()
                        .map(UserRole::getRole)
                        .flatMap(role -> role.getPermissions().stream())
                        .map(RolePermission::getPermission)
                        .map(Permission::getName)
                        .collect(Collectors.toSet()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));
    }

    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toDto)
                .toList();
    }

    public PermissionDto getPermissionById(Long permissionId) {
        var role = permissionRepository.findById(permissionId).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "permissionId", permissionId)
        );

        return permissionMapper.toDto(role);
    }
}

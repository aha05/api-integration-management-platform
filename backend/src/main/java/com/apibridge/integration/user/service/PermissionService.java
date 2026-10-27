package com.apibridge.integration.user.service;

import com.apibridge.integration.common.ResourceNotFoundException;
import com.apibridge.integration.user.dto.PermissionDto;
import com.apibridge.integration.user.mapper.PermissionMapper;
import com.apibridge.integration.user.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

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

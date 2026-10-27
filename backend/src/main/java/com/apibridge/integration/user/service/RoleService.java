package com.apibridge.integration.user.service;

import com.apibridge.integration.common.ResourceAlreadyExistException;
import com.apibridge.integration.common.ResourceNotFoundException;
import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.entity.RolePermission;
import com.apibridge.integration.user.entity.RolePermissionId;
import com.apibridge.integration.user.mapper.RoleMapper;
import com.apibridge.integration.user.repository.PermissionRepository;
import com.apibridge.integration.user.repository.RolePermissionRepository;
import com.apibridge.integration.user.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RoleMapper roleMapper;

    public List<RoleDto> getAllRoless() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .toList();
    }

    public RoleDto getRoleById(Long roleId) {
        var role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );

        return roleMapper.toDto(role);
    }

    public RoleDto create(RoleDto request) {
        if(roleRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException("Role", "Role-Name", request.getName());
        }
        var role = roleMapper.toEntity(request);
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public RoleDto update(Long roleId, RoleDto request) {
        var role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );

        roleMapper.update(request, role);
        var result = roleRepository.save(role);
        return roleMapper.toDto(result);
    }

    public void delete(Long roleId) {
        var role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );
        roleRepository.delete(role);
    }

    @Transactional
    public void assignRole(Long roleId, Long permissionId) {

        var role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );

        var permission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "permissionId", permissionId)
        );

        RolePermissionId id = new RolePermissionId(roleId, permissionId);

        if (rolePermissionRepository.existsById(id)) {
            throw new ResourceAlreadyExistException(
                    "RolePermission", "id", id
            );
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(id);
        rolePermission.setRole(role);
        rolePermission.setPermission(permission);

        rolePermissionRepository.save(rolePermission);
    }

    @Transactional
    public void detachRole(Long roleId, Long permissionId) {
        RolePermissionId id = new RolePermissionId(roleId, permissionId);

        RolePermission rolePermission = rolePermissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("RolePermission", "id", id)
        );

        rolePermissionRepository.delete(rolePermission);
    }
}

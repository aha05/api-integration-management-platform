package com.apibridge.integration.user.service;

import com.apibridge.integration.common.ResourceAlreadyExistException;
import com.apibridge.integration.common.ResourceNotFoundException;
import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.mapper.RoleMapper;
import com.apibridge.integration.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
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
        var user = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "roleId", roleId)
        );
        roleRepository.delete(user);
    }
}

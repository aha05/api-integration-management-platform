package com.apibridge.integration.user.controller;

import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoless();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody RoleDto request,
            UriComponentsBuilder uriBuilder
    ){
        var roleDto = roleService.create(request);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(roleDto.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleDto update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody RoleDto request
    ) {
        return roleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        roleService.delete(id);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void assignRole(
            @PathVariable(name = "roleId") Long roleId,
            @PathVariable(name = "permissionId") Long permissionId
    ){
        roleService.assignRole(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachRole(
            @PathVariable(name = "roleId") Long roleId,
            @PathVariable(name = "permissionId") Long permissionId){
        roleService.detachRole(roleId, permissionId);
    }
}

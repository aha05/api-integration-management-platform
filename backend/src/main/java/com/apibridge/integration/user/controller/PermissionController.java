package com.apibridge.integration.user.controller;

import com.apibridge.integration.user.dto.PermissionDto;
import com.apibridge.integration.user.dto.RoleDto;
import com.apibridge.integration.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PermissionDto> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PermissionDto getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getPermissionByUserId(@PathVariable Long id) {
        return permissionService.getUserPermissions(id);
    }
}

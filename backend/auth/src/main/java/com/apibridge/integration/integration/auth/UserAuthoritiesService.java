package com.apibridge.integration.integration.auth;

import com.apibridge.integration.integration.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthoritiesService {
    private final PermissionService permissionService;

    public List<GrantedAuthority> loadAuthorities(Long userId, List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        permissionService.getUserPermissions(userId)
                .forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission))
                );

        roles.forEach(role -> authorities.add(
                new SimpleGrantedAuthority(
                        role.startsWith("ROLE_") ? role : "ROLE_" + role
                )
        ));

        return authorities;
    }
}
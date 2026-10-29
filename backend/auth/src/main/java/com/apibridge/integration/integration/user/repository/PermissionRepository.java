package com.apibridge.integration.integration.user.repository;

import com.apibridge.integration.integration.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}

package com.apibridge.integration.user.repository;

import com.apibridge.integration.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
}

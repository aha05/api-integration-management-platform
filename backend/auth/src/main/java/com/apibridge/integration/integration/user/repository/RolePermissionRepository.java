package com.apibridge.integration.integration.user.repository;

import com.apibridge.integration.integration.user.entity.RolePermission;
import com.apibridge.integration.integration.user.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}

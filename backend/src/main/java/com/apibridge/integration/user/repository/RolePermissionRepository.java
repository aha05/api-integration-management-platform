package com.apibridge.integration.user.repository;

import com.apibridge.integration.user.entity.RolePermission;
import com.apibridge.integration.user.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}

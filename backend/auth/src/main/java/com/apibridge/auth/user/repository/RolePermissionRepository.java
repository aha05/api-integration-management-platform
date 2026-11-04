package com.apibridge.auth.user.repository;

import com.apibridge.auth.user.entity.RolePermission;
import com.apibridge.auth.user.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}

package com.apibridge.integration.integration.user.repository;

import com.apibridge.integration.integration.user.entity.UserRole;
import com.apibridge.integration.integration.user.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

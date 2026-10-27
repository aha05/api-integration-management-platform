package com.apibridge.integration.user.repository;

import com.apibridge.integration.user.entity.UserRole;
import com.apibridge.integration.user.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

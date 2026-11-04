package com.apibridge.auth.user.repository;

import com.apibridge.auth.user.entity.UserRole;
import com.apibridge.auth.user.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

package com.apibridge.integration.user.repository;

import com.apibridge.integration.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
}

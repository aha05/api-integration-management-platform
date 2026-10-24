package com.apibridge.integration.user.repository;

import com.apibridge.integration.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

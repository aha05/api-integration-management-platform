package com.apibridge.integration.integrations.repository;

import com.apibridge.integration.integrations.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrationRepository extends JpaRepository<Integration, Long> {
}

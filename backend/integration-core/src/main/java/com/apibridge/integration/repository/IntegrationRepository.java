package com.apibridge.integration.repository;

import com.apibridge.integration.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IntegrationRepository extends JpaRepository<Integration, Long> {
    List<Integration> findAllByUserId(Long userId);
    Optional<Integration> findByIdAndUserId(Long id, Long userId);
}

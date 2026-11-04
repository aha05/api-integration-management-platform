package com.apibridge.integration.mapper;

import com.apibridge.integration.dto.IntegrationRequest;
import com.apibridge.integration.dto.IntegrationResponse;
import com.apibridge.integration.dto.UpdateIntegrationRequest;
import com.apibridge.integration.entity.Integration;

public interface IntegrationMapper {
    IntegrationResponse toDto(Integration integration);
    Integration toEntity(IntegrationRequest request);
    Integration update(Integration integration, UpdateIntegrationRequest request);
}

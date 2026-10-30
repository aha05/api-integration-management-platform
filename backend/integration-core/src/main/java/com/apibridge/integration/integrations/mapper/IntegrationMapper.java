package com.apibridge.integration.integrations.mapper;

import com.apibridge.integration.integrations.dto.IntegrationRequest;
import com.apibridge.integration.integrations.dto.IntegrationResponse;
import com.apibridge.integration.integrations.entity.Integration;

public interface IntegrationMapper {
    IntegrationResponse toDto(Integration integration);
    Integration toEntity(IntegrationRequest request);
}

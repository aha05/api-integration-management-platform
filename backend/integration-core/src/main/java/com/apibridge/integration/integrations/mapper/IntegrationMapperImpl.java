package com.apibridge.integration.integrations.mapper;

import com.apibridge.integration.integrations.dto.IntegrationRequest;
import com.apibridge.integration.integrations.dto.IntegrationResponse;
import com.apibridge.integration.integrations.entity.Integration;
import org.springframework.stereotype.Component;

@Component
public class IntegrationMapperImpl implements IntegrationMapper{
    @Override
    public IntegrationResponse toDto(Integration integration) {
        return IntegrationResponse.builder()
                .id(integration.getId())
                .name(integration.getName())
                .description(integration.getDescription())
                .baseUrl(integration.getBaseUrl())
                .status(integration.getStatus())
                .build();
    }

    @Override
    public Integration toEntity(IntegrationRequest request) {
        Integration integration = new Integration();
        integration.setName(request.getName());
        integration.setDescription(request.getDescription());
        integration.setBaseUrl(request.getBaseUrl());
        return integration;
    }
}

package com.apibridge.integration.mapper;

import com.apibridge.integration.dto.IntegrationRequest;
import com.apibridge.integration.dto.IntegrationResponse;
import com.apibridge.integration.dto.UpdateIntegrationRequest;
import com.apibridge.integration.entity.Integration;
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
                .userId(integration.getUserId())
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

    @Override
    public Integration update(Integration integration, UpdateIntegrationRequest request) {
        if (request.getName() != null){
            integration.setName(request.getName());
        }

        if (request.getDescription() != null){
            integration.setDescription(request.getDescription());
        }

        if (request.getBaseUrl() != null){
            integration.setBaseUrl(request.getBaseUrl());
        }
        return integration;
    }
}

package com.apibridge.integration.service;

import com.apibridge.auth.common.ResourceNotFoundException;
import com.apibridge.integration.dto.IntegrationRequest;
import com.apibridge.integration.dto.IntegrationResponse;
import com.apibridge.integration.dto.UpdateIntegrationRequest;
import com.apibridge.integration.entity.IntegrationStatus;
import com.apibridge.integration.mapper.IntegrationMapper;
import com.apibridge.integration.repository.IntegrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;
    private final IntegrationMapper integrationMapper;

    public IntegrationResponse create(IntegrationRequest request, Long userId){
        var integration = integrationMapper.toEntity(request);
        integration.setUserId(userId);
        integrationRepository.save(integration);
        return integrationMapper.toDto(integration);
    }

    public List<IntegrationResponse> getAll(Long userId){
        return integrationRepository.findAllByUserId(userId).stream()
                .map(integrationMapper::toDto)
                .toList();
    }

    public IntegrationResponse getById(Long id, Long userId){
        var integration = integrationRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new ResourceNotFoundException("Integration")
        );
        return integrationMapper.toDto(integration);
    }

    public IntegrationResponse update(Long id, UpdateIntegrationRequest request, Long userId){
        var integration = integrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Integration", "Id", id)
        );
        integrationMapper.update(integration, request);
        integration.setUserId(userId);
        integrationRepository.save(integration);
        return integrationMapper.toDto(integration);
    }

    public IntegrationResponse updateStatus(Long id, boolean active, Long userId){
        var integration = integrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Integration", "Id", id)
        );
        if (active) {
            integration.setStatus(IntegrationStatus.ACTIVE);
        } else {
            integration.setStatus(IntegrationStatus.INACTIVE);
        }
        integrationRepository.save(integration);
        return integrationMapper.toDto(integration);
    }

    public void delete(Long id, Long userId){
        integrationRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new ResourceNotFoundException("Integration")
        );
        integrationRepository.deleteById(id);
    }
}

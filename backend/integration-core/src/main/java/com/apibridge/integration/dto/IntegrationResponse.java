package com.apibridge.integration.dto;

import com.apibridge.integration.entity.IntegrationStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IntegrationResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String baseUrl;
    private IntegrationStatus status;
    private Long userId;
}

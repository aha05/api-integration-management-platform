package com.apibridge.integration.integrations.dto;

import com.apibridge.integration.integrations.entity.IntegrationStatus;
import jakarta.persistence.*;
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
}

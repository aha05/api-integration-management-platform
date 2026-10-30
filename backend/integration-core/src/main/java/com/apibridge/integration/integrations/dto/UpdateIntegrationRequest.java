package com.apibridge.integration.integrations.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateIntegrationRequest {
    @Size(min = 2, max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 500)
    private String baseUrl;
}

package com.apibridge.integration.integrations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IntegrationRequest {
    @NotBlank(message = "Integration name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotBlank(message = "Base URL is required")
    @Size(max = 500)
    private String baseUrl;
}

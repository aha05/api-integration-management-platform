package com.apibridge.integration.integrations.service;

import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class IntegrationUrlValidator {

    public void validate(String baseUrl) {

        try {
            URI uri = URI.create(baseUrl);

            if (!uri.isAbsolute()
                    || uri.getHost() == null
                    || !("https".equalsIgnoreCase(uri.getScheme())
                    || "http".equalsIgnoreCase(uri.getScheme()))) {

                throw new IllegalArgumentException(
                        "Invalid integration base URL"
                );
            }

            if (uri.getUserInfo() != null) {
                throw new IllegalArgumentException(
                        "URL credentials are not allowed"
                );
            }

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Invalid integration base URL"
            );
        }
    }
}

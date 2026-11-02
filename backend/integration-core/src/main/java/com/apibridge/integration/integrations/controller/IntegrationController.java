package com.apibridge.integration.integrations.controller;
import com.apibridge.integration.integration.auth.UserPrincipal;
import com.apibridge.integration.integrations.dto.IntegrationRequest;
import com.apibridge.integration.integrations.dto.IntegrationResponse;
import com.apibridge.integration.integrations.dto.UpdateIntegrationRequest;
import com.apibridge.integration.integrations.service.IntegrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/integrations")
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationService integrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public IntegrationResponse create(
            @Valid @RequestBody IntegrationRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return integrationService.create(
                request,
                principal.getId()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IntegrationResponse> getAll(
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        return integrationService.getAll(principal.getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IntegrationResponse getById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return integrationService.getById(id, principal.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public IntegrationResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateIntegrationRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return integrationService.update(
                        id, request, principal.getId()
                );
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public IntegrationResponse updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return integrationService.updateStatus(
                        id, active, principal.getId()
                );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        integrationService.delete(id, principal.getId());
    }
}
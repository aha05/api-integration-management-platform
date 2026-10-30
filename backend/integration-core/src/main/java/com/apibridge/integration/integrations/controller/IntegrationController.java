package com.apibridge.integration.integrations.controller;


import com.apibridge.integration.integrations.dto.IntegrationRequest;
import com.apibridge.integration.integrations.dto.IntegrationResponse;
import com.apibridge.integration.integrations.dto.UpdateIntegrationRequest;
import com.apibridge.integration.integrations.service.IntegrationService;
import com.sun.security.auth.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/integrations")
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationService integrationService;

    @PostMapping
    public ResponseEntity<IntegrationResponse> create(
            @Valid @RequestBody IntegrationRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        IntegrationResponse response = integrationService.create(
                request,
                principal.getId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<IntegrationResponse>> getAll(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return ResponseEntity.ok(
                integrationService.getAll(principal.getId())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntegrationResponse> getById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return ResponseEntity.ok(
                integrationService.getById(id, principal.getId())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntegrationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateIntegrationRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return ResponseEntity.ok(
                integrationService.update(
                        id, request, principal.getId()
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<IntegrationResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        return ResponseEntity.ok(
                integrationService.updateStatus(
                        id, active, principal.getId()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        integrationService.delete(id, principal.getId());

        return ResponseEntity.noContent().build();
    }
}
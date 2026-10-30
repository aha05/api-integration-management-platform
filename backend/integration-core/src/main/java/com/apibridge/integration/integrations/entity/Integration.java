package com.apibridge.integration.integrations.entity;

import com.apibridge.integration.integrations.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "integrations")
public class Integration extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "base_url")
    private String baseUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IntegrationStatus status;
}

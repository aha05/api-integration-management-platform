package com.apibridge.integration.integration.user.entity;

import com.apibridge.integration.integration.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<UserRole> users = new ArrayList<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<RolePermission> permissions = new ArrayList<>();
}

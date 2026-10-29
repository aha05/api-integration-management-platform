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
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();
}

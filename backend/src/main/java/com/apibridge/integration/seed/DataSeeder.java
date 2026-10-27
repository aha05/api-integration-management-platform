package com.apibridge.integration.seed;

import com.apibridge.integration.user.entity.*;
import com.apibridge.integration.user.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            UserRoleRepository userRoleRepository,
            RolePermissionRepository rolePermissionRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        // 1. Create permissions
        Permission userRead = getOrCreatePermission(
                "USER_READ", "Read users");

        Permission userCreate = getOrCreatePermission(
                "USER_CREATE", "Create users");

        Permission userUpdate = getOrCreatePermission(
                "USER_UPDATE", "Update users");

        Permission userDelete = getOrCreatePermission(
                "USER_DELETE", "Delete users");

        Permission roleManage = getOrCreatePermission(
                "ROLE_MANAGE", "Manage roles and permissions");

        Permission integrationManage = getOrCreatePermission(
                "INTEGRATION_MANAGE", "Manage API integrations");

        // 2. Create roles
        Role adminRole = getOrCreateRole(
                "ADMIN", "Administrator");

        Role userRole = getOrCreateRole(
                "USER", "Standard user");

        // 3. Assign permissions to roles
        List.of(
                userRead,
                userCreate,
                userUpdate,
                userDelete,
                roleManage,
                integrationManage
        ).forEach(permission ->
                assignPermission(adminRole, permission));

        assignPermission(userRole, userRead);

        // 4. Create the admin user
        User admin = userRepository
                .findByEmail("admin@example.com")
                .orElseGet(() -> {
                    User user = new User();
                    user.setName("Administrator");
                    user.setEmail("admin@example.com");
                    user.setPassword(
                            passwordEncoder.encode("admin"));
                    user.setActive(true);

                    return userRepository.save(user);
                });

        // 5. Assign ADMIN role to the admin user
        assignRole(admin, adminRole);
    }

    private Permission getOrCreatePermission(
            String name, String description) {

        return permissionRepository.findByName(name)
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setDescription(description);

                    return permissionRepository.save(permission);
                });
    }

    private Role getOrCreateRole(
            String name, String description) {

        return roleRepository.findByName(name)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(name);
                    role.setDescription(description);

                    return roleRepository.save(role);
                });
    }

    private void assignPermission(
            Role role, Permission permission) {

        RolePermissionId id = new RolePermissionId(
                role.getId(), permission.getId());

        if (!rolePermissionRepository.existsById(id)) {
            RolePermission assignment = new RolePermission();
            assignment.setId(id);
            assignment.setRole(role);
            assignment.setPermission(permission);

            rolePermissionRepository.save(assignment);
        }
    }

    private void assignRole(User user, Role role) {

        UserRoleId id = new UserRoleId(
                user.getId(), role.getId());

        if (!userRoleRepository.existsById(id)) {
            UserRole assignment = new UserRole();
            assignment.setId(id);
            assignment.setUser(user);
            assignment.setRole(role);

            userRoleRepository.save(assignment);
        }
    }
}


create table users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(50)  NOT NULL,
    email VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active        BOOLEAN      NOT NULL DEFAULT true,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   VARCHAR(100)
);

create table roles
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(50)NOT NULL UNIQUE,
    description TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   VARCHAR(100)
);

create table permissions
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(50)NOT NULL UNIQUE,
    description TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   VARCHAR(100)
);


CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id) REFERENCES roles (id)
        ON DELETE CASCADE
);

CREATE TABLE role_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,

    PRIMARY KEY (role_id, permission_id),

    CONSTRAINT fk_role_permissions_role
        FOREIGN KEY (role_id) REFERENCES roles (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_role_permissions_permission
        FOREIGN KEY (permission_id) REFERENCES permissions (id)
        ON DELETE CASCADE
);




CREATE TABLE integrations
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    base_url VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   VARCHAR(100),

    INDEX idx_integration_status (status),

    CONSTRAINT chk_integration_status
      CHECK (status IN ('ACTIVE', 'INACTIVE'))
);
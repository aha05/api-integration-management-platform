package com.apibridge.integration.integrations.common;

import com.apibridge.integration.integration.user.entity.RolePermissionId;
import com.apibridge.integration.integration.user.entity.UserRoleId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s not found with the given input.", resourceName));
    }


}

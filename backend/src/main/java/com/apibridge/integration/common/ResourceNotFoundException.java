package com.apibridge.integration.common;

import com.apibridge.integration.user.entity.RolePermissionId;
import com.apibridge.integration.user.entity.UserRoleId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName, String fieldName, UserRoleId fieldValue) {
        super(String.format("%s not found with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName, String fieldName, RolePermissionId fieldValue) {
        super(String.format("%s not found with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

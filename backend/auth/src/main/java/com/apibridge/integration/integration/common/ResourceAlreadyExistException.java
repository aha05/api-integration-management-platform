package com.apibridge.integration.integration.common;

import com.apibridge.integration.integration.user.entity.RolePermissionId;
import com.apibridge.integration.integration.user.entity.UserRoleId;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s already exists with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceAlreadyExistException(String resourceName, String fieldName, UserRoleId fieldValue) {
        super(String.format("%s already exists with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceAlreadyExistException(String resourceName, String fieldName, RolePermissionId fieldValue) {
        super(String.format("%s already exists with the given input %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

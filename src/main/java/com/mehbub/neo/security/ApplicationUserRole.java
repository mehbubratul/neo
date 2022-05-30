package com.mehbub.neo.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
        ApplicationUserPermission.CUSTOMER_READ, 
        ApplicationUserPermission.CUSTOMER_WRITE, 
        ApplicationUserPermission.CATEGORY_READ,
        ApplicationUserPermission.CATEGORY_WRITE, 
        ApplicationUserPermission.PRODUCT_READ, 
        ApplicationUserPermission.PRODUCT_WRITE)), 
    ADMIN_TRAINEE(Sets.newHashSet(
        ApplicationUserPermission.CUSTOMER_READ, 
        ApplicationUserPermission.CATEGORY_READ,
        ApplicationUserPermission.PRODUCT_READ
    ));

    private final Set<ApplicationUserPermission> permissions;
    
    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}

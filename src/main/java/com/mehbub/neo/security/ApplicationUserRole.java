package com.mehbub.neo.security;

import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Roles can be seen as coarse-grained GrantedAuthorities represented as a String with prefix with “ROLE“. 
 * We can use a role directly in Spring security application by using hasRole("CUSTOMER"). 
 * For few simple applications, we can think of Roles as a GrantedAuthorities. 
 * Here are some example for the Spring security Roles.
        ROLE_ADMIN
        ROLE_MANAGER
        ROLE_USER
 * We can also use the roles as container for authorities or privileges.
 * This approach provides flexibility to map roles based on business rules. 
 * Let’s take look at few examples to understand it clearly.
        * User with ROLE_ADMIN      => role have the authorities to READ,DELETE,WRITE,UPDATE.
        * A user with role ROLE_USER=> has authority to READ only.
        * User with ROLE_MANAGER    => can perform READ, WRITE and UPDATE operations.
 * We can also use the roles as container for authorities or privileges.
 * 
 * All of this can be easily done using a custom UserDetailsService which take care to collect all roles 
 * and all operations and make them available by the method getAuthorities().
 */

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

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions =  getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

package com.myapp.georgewannabe.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.myapp.georgewannabe.models.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(USER_READ, USER_WRITE)
    ),ADMIN(Set.of(ADMIN_READ,ADMIN_WRITE));
    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

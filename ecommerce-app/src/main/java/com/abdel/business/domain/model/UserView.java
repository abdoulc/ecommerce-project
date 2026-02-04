package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.UserId;
import com.abdel.business.domain.valueobject.Username;

import java.util.HashSet;
import java.util.Set;

public class UserView {
    private final UserId id;
    private final Username username;
    private final String role;
    private final boolean enabled;
    private final Set<String> authorities;


    public UserView(UserId id, Username username, String role, boolean enabled, Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.enabled = enabled;
        this.authorities = authorities;
    }
    public UserId getId() {
        return id;
    }
    public Username getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public Set<String> getAuthorities() {
        return new HashSet<>(authorities);
    }

}

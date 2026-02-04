package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.UserId;
import com.abdel.business.domain.valueobject.Email;
import com.abdel.business.domain.valueobject.Password;

import java.util.HashSet;
import java.util.Set;

public class User {

    private UserId userId;

    private String username;
    private Email email;

    private Password password;

    private final Set<Role> roles ;


    private User(final UserId userId, String username, Email email, Password password,Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(roles);
    }

    public static User create(UserId userId, String username, Email email, Password password,  Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role");
        }
        return new User(userId, username, email, password, roles);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roles);
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}

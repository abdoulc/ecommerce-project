package com.abdel.infrastructure.auth;

import com.abdel.infrastructure.auth.SecurityUser;
import com.abdel.infrastructure.persistence.entity.RoleEntity;
import com.abdel.infrastructure.persistence.entity.UserEntiy;
import com.abdel.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SpringDataUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SecurityUser loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntiy user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().forEach(role -> {
            authorities.add(
                    new SimpleGrantedAuthority(role.getName())
            );

            role.getPermissions().forEach(permission ->
                    authorities.add(
                            new SimpleGrantedAuthority(permission.getName())
                    )
            );
        });

        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                roleNames(user),
                true,
                authorities
        );
    }
    private String roleNames(UserEntiy user) {
        return user.getRoles()
                .stream()
                .findFirst()
                .map(RoleEntity::getName)
                .orElse("USER");
    }
}

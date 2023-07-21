package com.myhealth.oHealth.model.security;

import com.myhealth.oHealth.model.domain.User;
import com.myhealth.oHealth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmailAddress(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " " +
                        "not found.")
        );

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(
                        role.getTitle()
                )))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmailAddress(),
                user.getPassword(),
                authorities
        );
    }
}

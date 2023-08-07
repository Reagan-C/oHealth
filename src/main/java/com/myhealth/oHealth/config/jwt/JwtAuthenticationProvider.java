package com.myhealth.oHealth.config.jwt;

import com.myhealth.oHealth.model.domain.Role;
import com.myhealth.oHealth.model.domain.User;
import com.myhealth.oHealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailAddress = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = repository.findByEmailAddress(emailAddress).orElseThrow(
                ()-> new BadCredentialsException("Username incorrect")
        );

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(emailAddress, password, getUserRoles(user.getRoles()));
        }
        throw new BadCredentialsException("Incorrect password");
    }

    private Collection<? extends GrantedAuthority> getUserRoles(List<Role> roles) {
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        for (Role role : roles) {
            userAuthorities.add(new SimpleGrantedAuthority(role.getTitle()));
        }
        return userAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

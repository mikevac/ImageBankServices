package com.vac.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vac.main.encoders.IBPasswordEncoder;
import com.vac.main.exceptions.UserNotFoundException;

@Component
public class IBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IBUserDetailsService userDetailsService;

    @Autowired
    private IBPasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (userDetails != null) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return getToken(name, password);
            }

        }
        throw new UserNotFoundException();

    }

    private static UsernamePasswordAuthenticationToken getToken(String name, String pass) {
        List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails principal = new User(name, pass, grants);
        return new UsernamePasswordAuthenticationToken(principal, pass, grants);

    }
}

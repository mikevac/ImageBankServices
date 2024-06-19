package com.vac.main.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vac.main.data.dto.UserDto;

import lombok.Getter;

@Getter
public class IBUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String emailAddress;
    private final String timeZone;
    private final Collection<SimpleGrantedAuthority> authorities;

    public IBUserDetails(UserDto user) {
        authorities = user.roles().stream().map(r -> new SimpleGrantedAuthority(r.role())).toList();
        userName = user.handle();
        firstName = user.firstName();
        lastName = user.lastName();
        password = user.password();
        emailAddress = user.emailAddress();
        timeZone = user.timeZone();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

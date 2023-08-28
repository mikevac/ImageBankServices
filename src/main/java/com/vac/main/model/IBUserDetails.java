package com.vac.main.model;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class IBUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String userName;
    private final String password;
    private final String emailAddress;
    private final String phoneNumber;
    private final String realName;
    private final Collection<SimpleGrantedAuthority> authorities;

    public IBUserDetails(UserDto user, List<RoleDto> roles) {
        authorities = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).toList();
        userName = user.userName();
        password = user.password();
        emailAddress = user.emailAddress();
        phoneNumber = user.phoneNumber();
        realName = user.realName();
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

package com.vac.main.model;

import java.math.BigInteger;
import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.vac.main.data.dto.UserDto;

import lombok.Getter;

@Getter
public class IBUser extends User {

    private static final long serialVersionUID = 1L;
    private final BigInteger userId;
    private final String emailAddress;
    private final String timeZone;

    public IBUser(UserDto user) {
        super(user.userName(), user.password(), true, true,
                true, true, user.roles().stream().map(r -> new SimpleGrantedAuthority(r.role())).toList());
        userId = user.userId();
        emailAddress = user.emailAddress();
        timeZone = user.timeZone();
    }

    public IBUser(BigInteger userId, String userName, String password, String[] roles, String firstName,
            String lastName, String emailAddress, String timezone) {
        super(userName, password, true, true, true, true,
                Arrays.asList(roles).stream().map(r -> new SimpleGrantedAuthority(r)).toList());
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.timeZone = timezone;
    }

}

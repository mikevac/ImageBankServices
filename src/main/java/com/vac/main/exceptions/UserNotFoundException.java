package com.vac.main.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("Invalid User");
    }
}

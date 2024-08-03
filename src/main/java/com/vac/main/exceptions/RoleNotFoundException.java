package com.vac.main.exceptions;

public class RoleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RoleNotFoundException() {
        super();
    }

    public RoleNotFoundException(String msg) {
        super(msg);
    }
}

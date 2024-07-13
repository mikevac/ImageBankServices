package com.vac.main.constants;

public class RegExp {

    private RegExp() {
    }

    public static final String ALPHA_NUMERIC = "^[A-Za-z0-9]*$";
    public static final String NUMERIC = "^[0-9]*$";
    public static final String ALPHABETIC = "^[a-zA-Z]*$";
    public static final String PASSWORD_CHARS = "^[0-9A-Za-z$#@!*]*$";
    public static final String EMAIL_ADDR = "^[a-zA-Z0-9\\.]*@[a-zA-Z0-9\\.]*$";

}

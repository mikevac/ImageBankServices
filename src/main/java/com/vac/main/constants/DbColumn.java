package com.vac.main.constants;

/**
 * DbColumn
 * 
 * Class documents all valid column names used as parameter source in
 * NamedParameterJdbcTemplate
 */
public class DbColumn {

    // never try to instantiate this class
    private DbColumn() {
    }

    public static final String HANDLE = "HANDLE";
    public static final String USER_ROLE_ID = "USER_ROLE_ID";
    public static final String USER_ID = "USER_ID";
    public static final String ROLE = "ROLE";
    public static final String EXPIRATION = "EXPIRATION";
}

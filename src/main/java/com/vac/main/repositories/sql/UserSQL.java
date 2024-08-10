package com.vac.main.repositories.sql;

public class UserSQL {

    private UserSQL() {
    }

    // @formatter:off
    public static final String FETCH_USER = """
        FROM com.vac.main.data.entity.UserEntity 
        WHERE userName = :userName
          AND deleted = false""";
    // @formatter:on

}

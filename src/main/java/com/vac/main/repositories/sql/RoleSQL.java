package com.vac.main.repositories.sql;

public class RoleSQL {

    private RoleSQL() {
    }

    public static final String FIND_ROLE_BY_NAME = """
            FROM com.vac.main.data.entity.RoleEntity r
            WHERE r.role = :role
            """;
    public static final String FETCH_USER_ROLES = """
            FROM com.vac.main.data.entity.RoleEntity r
            WHERE r.userId = :userId;
            """;
}

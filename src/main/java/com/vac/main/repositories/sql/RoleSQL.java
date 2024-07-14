package com.vac.main.repositories.sql;

public class RoleSQL {

    private RoleSQL() {
    }

    public static final String FIND_ROLE_BY_NAME = """
            FROM com.vac.main.data.entity.RoleEntity
            WHERE role = :role
            """;

}

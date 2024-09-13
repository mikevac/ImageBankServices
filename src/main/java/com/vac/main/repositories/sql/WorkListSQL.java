package com.vac.main.repositories.sql;

public class WorkListSQL {

    private WorkListSQL() {
    }

    public static final String GET_LIST = """
            FROM com.vac.main.data.entity.WorkListEntity we
            WHERE we.userEntity.userId = :userId
            order by we.name
            """;

    public static final String GET_COUNT = """
            SELECT count(*)
            FROM com.vac.main.data.entity.WorkListEntity we
            WHERE we.userEntity.userId = :userId
            """;
}

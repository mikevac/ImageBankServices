package com.vac.main.repositories.sql;

public class DocumentSQL {

    private DocumentSQL() {
    }

    public static final String GET_LIST = """
            FROM com.vac.main.data.entity.DocumentEntity de
            where de.owner.user_id = :userId
            order by de.file_name
            """;
    public static final String GET_ROW_COUNT = """
            SELECT COUNT(*)
            FROM com.vac.main.data.entity.DocumentEntity de
            where de.owner.user_id = :userId
            """;
    public static final String GET_DOC_FOR_WORKLIST = """
            FROM com.vac.main.data.entity.DocumentEntity de
            where de.owner.user_id = :userId
            and de.worklist_id = :worklist
            order by de.file_name
            """;
}

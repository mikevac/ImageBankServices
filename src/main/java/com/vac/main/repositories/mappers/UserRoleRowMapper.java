package com.vac.main.repositories.mappers;

import com.vac.main.constants.DbColumn;
import com.vac.main.data.dto.RoleDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * <h1>UserRoleRowMapper</h1>
 * <p>
 * Class is the row mapper for use when obtaining a list of user roles from the database
 * <pre>
 *     List&lt;UserRoleDto&gt; userRoleList = jdbcTemplate("SELECT...", MapSqlParamaterSource, new UserRoleRowMapper());
 * </pre>
 * </p>
 */
public class UserRoleRowMapper implements RowMapper<RoleDto> {
    @Override
    public RoleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        var roleDto = new RoleDto();
        roleDto.setRole(rs.getString(DbColumn.ROLE));
        roleDto.setUserRoleId(rs.getInt(DbColumn.USER_ROLE_ID));
        roleDto.setExpiration(OffsetDateTime.ofInstant(rs.getTimestamp(DbColumn.EXPIRATION).toInstant(), ZoneId.of("UTC")));
        roleDto.setUserId(rs.getInt(DbColumn.USER_ID));
        return roleDto;
    }
}

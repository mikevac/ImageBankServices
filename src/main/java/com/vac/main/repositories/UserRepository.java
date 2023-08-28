package com.vac.main.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vac.main.constants.DbColumn;
import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.repositories.mappers.UserRoleRowMapper;

/**
 * <h1>UserRepository</h1>
 * <p>
 * Class is the repository service bean that executes the sql to access the user
 * and user role tables.
 * </p>
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// @formatter:off
	static final String SQL_FETCH_ROLES = """
			select
				user_role_id,
			    user_id,
			    role,
			    expiration
			from ibank.privilage
			where user_id = :userId""";
	static final String SQL_FETCH_USER = """
			select
		         user_id
		       , handle
		       , first_name
		       , last_name
		       , email_address
		       , date_established
		       , password
		       , active
		       , deleted
		       , time_zone
	      from ibank.user
	      where handle = :handle""";
	// @formatter:on
	/**
	 * <h1>fetchRoles</h1>
	 * <p>
	 * Given a valid userId, this method will fetch a list of roles. If the userId
	 * is null the method will return an empty list.
	 * </p>
	 *
	 * @param userId an Integer containing a valid user id.
	 * @return a List of roles, or an empty list if the userId is invalid or null
	 */
	public List<RoleDto> fetchRoles(Integer userId) {
		if (userId == null) {
			return new ArrayList<>();
		}
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(DbColumn.USER_ID, userId);
		return jdbcTemplate.query(SQL_FETCH_ROLES, source, new UserRoleRowMapper());
	}

	public UserDto findUser(String handle) {
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(DbColumn.HANDLE, handle);
		return jdbcTemplate.queryForObject(SQL_FETCH_USER, source, UserDto.class);

	}
}

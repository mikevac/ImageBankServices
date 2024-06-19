package com.vac.main.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.data.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * <h1>UserRepository</h1>
 * <p>
 * Class is the repository service bean that executes the sql to access the user
 * and user role tables.
 * </p>
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    // @formatter:off
	static final String SQL_FETCH_USER = """
		FROM com.vac.main.data.entity.UserEntity 
	    WHERE handle = :handle
	      AND deleted = false""";
	// @formatter:on

    public UserDto findUser(String handle) {
        // @formatter:off
        UserEntity user = 
                (UserEntity) em.createQuery(SQL_FETCH_USER)
                .setParameter("handle", handle)
                .getSingleResult();
        Set<RoleDto> roles = user.getRoles()
                .stream()
                .map(r -> new RoleDto(r.getUserRoleId(), r.getUser().getUserId(), r.getRole(), r.getExpiration().toLocalDate() ))
                .collect(Collectors.toSet());
     
        return new UserDto(user.getUserId(), 
                user.getHandle(), 
                user.getFirstName(), 
                user.getLastName(),
                user.getEmailAddress(), 
                user.getDateEstablished().toLocalDate(),  
                user.getPassword(),
                user.getActive(), 
                user.getTimeZone(),
                roles);
        // @formatter:on
    }
}

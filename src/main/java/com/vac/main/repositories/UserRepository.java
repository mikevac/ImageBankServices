package com.vac.main.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.data.entity.RoleEntity;
import com.vac.main.data.entity.UserEntity;
import com.vac.main.data.entity.UserRoleEntity;

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
         Set<RoleDto> roles = user.getUserRoleEntities()
                .stream()
                .map((r) -> new RoleDto(r.getRoleEntity().getRoleId(), r.getRoleEntity().getRole()))
                .collect(Collectors.toSet());
     
        return new UserDto(user.getUserId(), 
                user.getUserName(), 
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

    public void createUser(UserDto userDto, RoleDto roleDto) {
        try {
            var userEntity = new UserEntity(userDto, roleDto);
            em.persist(userEntity);
            var roleEntity = em.find(RoleEntity.class, roleDto.roleId());
            var userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserEntity(userEntity);
            userRoleEntity.setRoleEntity(roleEntity);
            userRoleEntity.setExpiration(Date.valueOf(LocalDate.now().plusYears(1L)));
            em.persist(userRoleEntity);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}

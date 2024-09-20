package com.vac.main.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.vac.main.constants.RepositoryStatus;
import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.data.entity.RoleEntity;
import com.vac.main.data.entity.UserEntity;
import com.vac.main.data.entity.UserRoleEntity;
import com.vac.main.exceptions.UserNotFoundException;
import com.vac.main.repositories.response.GenericRepositoryResponse;
import com.vac.main.repositories.sql.RoleSQL;
import com.vac.main.repositories.sql.UserSQL;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

/**
 * UserRepository
 * 
 * Class is the repository service bean that executes the sql to access the user
 * and user role tables.
 * 
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public UserDto findUser(String userName) {
        //@formatter:off
        try {
            
            UserEntity user = em.createQuery(UserSQL.FETCH_USER, UserEntity.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
            List<RoleEntity> roleEntityList = em.createQuery(RoleSQL.FETCH_USER_ROLES, RoleEntity.class)
                    .setParameter("userId", user.getUserId())
                    .getResultList();
            Set<RoleDto> roles = roleEntityList
                    .stream()
                    .map((r) -> new RoleDto(r.getRoleId(), r.getDescription(), r.getRole()))
                    .collect(Collectors.toSet());
            return new UserDto(user.getUserId(), 
                    user.getUserName(), 
                    user.getEmailAddress(), 
                    user.getDateEstablished().toLocalDate(),  
                    user.getPassword(),
                    user.getActive(), 
                    user.getTimeZone(),
                    roles);
        } catch (NoResultException nre) {
            throw new UserNotFoundException();
        }
        // @formatter:on
    }

    public GenericRepositoryResponse createUser(UserDto userDto, RoleDto roleDto) {
        try {
            var userEntity = new UserEntity(userDto, roleDto);
            var roleEntity = em.find(RoleEntity.class, roleDto.roleId());
            var userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserEntity(userEntity);
            userRoleEntity.setRoleEntity(roleEntity);
            userRoleEntity.setExpiration(Date.valueOf(LocalDate.now().plusYears(1L)));
            em.persist(userEntity);
            return new GenericRepositoryResponse(RepositoryStatus.SUCCESS, "User created successfully");
        } catch (EntityExistsException x) {
            return new GenericRepositoryResponse(RepositoryStatus.FAILURE, "User already exists");
        }
    }
}

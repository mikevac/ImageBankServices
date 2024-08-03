package com.vac.main.repositories;

import org.springframework.stereotype.Repository;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.entity.RoleEntity;
import com.vac.main.repositories.sql.RoleSQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Find a role
     * 
     * This finds a role entity by role name.
     * 
     * @param role a string containing the role name e.g. "USER"
     * @return a {@link RoleDto} object or null
     */
    public RoleDto find(String role) {

        RoleEntity re = (RoleEntity) em
                .createQuery(RoleSQL.FIND_ROLE_BY_NAME)
                .setParameter("role", role)
                .getSingleResult();
        return new RoleDto(re.getRoleId(), re.getRole());
    }
}

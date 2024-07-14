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

    public RoleDto find(String role) {

        //@formatter:off
        RoleEntity re = (RoleEntity) em.createQuery(RoleSQL.FIND_ROLE_BY_NAME)
                .setParameter("role", role)
                .getSingleResult();
        return new RoleDto(re.getRoleId(), re.getRole());
        //@formatter:on
    }
}

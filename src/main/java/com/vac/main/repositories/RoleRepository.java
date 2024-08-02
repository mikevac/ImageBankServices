package com.vac.main.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.entity.RoleEntity;
import com.vac.main.repositories.sql.RoleSQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<RoleDto> find(String role) {

        Optional<RoleDto> returnValue;
        try {
            //@formatter:off
            RoleEntity re = (RoleEntity) em.createQuery(RoleSQL.FIND_ROLE_BY_NAME)
                    .setParameter("role", role)
                    .getSingleResult();
            //@formatter:on
            returnValue = Optional.of(new RoleDto(re.getRoleId(), re.getRole()));
        } catch (NoResultException nre) {
            return returnValue = Optional.empty();
        }
        return returnValue;
    }
}

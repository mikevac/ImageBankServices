package com.vac.main.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vac.main.data.entity.WorkListEntity;
import com.vac.main.repositories.sql.WorkListSQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class WorkListRepository {

    @PersistenceContext
    private EntityManager em;

    public List<WorkListEntity> fetchPage(BigInteger userId, Long pageNumber, Long pageSize) {
        Long offset = (pageNumber - 1L) * pageSize;
        return em.createQuery(WorkListSQL.GET_LIST, WorkListEntity.class)
                .setParameter("userId", userId)
                .setFirstResult(offset.intValue())
                .setMaxResults(pageSize.intValue())
                .getResultList();
    }

    public Long count(BigInteger userId) {
        return em.createQuery(WorkListSQL.GET_COUNT, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

}

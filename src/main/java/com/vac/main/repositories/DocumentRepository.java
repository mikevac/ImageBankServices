package com.vac.main.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vac.main.data.entity.DocumentEntity;
import com.vac.main.repositories.sql.DocumentSQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DocumentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<DocumentEntity> getList(BigInteger userId, Long pageNumber, Long pageSize) {
        Long startOffset = (pageNumber - 1L) * pageSize;
        return em.createQuery(DocumentSQL.GET_LIST, DocumentEntity.class)
                .setParameter("userId", userId)
                .setMaxResults(pageSize.intValue())
                .setFirstResult(startOffset.intValue())
                .getResultList();
    }

    public Long getRowCount(BigInteger userId) {
        return (Long) em.createQuery(DocumentSQL.GET_ROW_COUNT)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public List<DocumentEntity> getDocForWorklist(BigInteger userId, Long pageNumber, Long pageSize,
            BigInteger workListId) {
        Long startOffset = (pageNumber - 1L) * pageSize;
        return em.createQuery(DocumentSQL.GET_DOC_FOR_WORKLIST, DocumentEntity.class)
                .setParameter("userId", userId)
                .setParameter("worklistId", workListId)
                .setMaxResults(pageSize.intValue())
                .setFirstResult(startOffset.intValue())
                .getResultList();
    }

}

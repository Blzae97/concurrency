package com.blaze.semaphore.domain.docs.repository;

import com.blaze.semaphore.domain.docs.adapter.DocsAdapter;
import com.blaze.semaphore.domain.docs.entity.Docs;
import com.blaze.semaphore.domain.docs.mapper.DocsMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DocsJpaRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public DocsAdapter save(Docs docs) {

        if (docs.getDocsId() == null) {
            em.persist(docs);
        } else {
            em.merge(docs);
        }

        return new DocsAdapter(docs);
    }

    public DocsAdapter findByDocsId(Long docsId) {
        String jpql = """
                SELECT d\s
                FROM Docs d\s
                WHERE d.docsId = :docsId
                """;

        TypedQuery<Docs> docsTypedQuery = em.createQuery(jpql, Docs.class);
        docsTypedQuery.setParameter("docsId", docsId);

        try {
            Docs docs = docsTypedQuery.getSingleResult();
            DocsMapper docsMapper = new DocsMapper();
            return docsMapper.toAdapter(docs);
        } catch (NoResultException noResultException) {
            throw new IllegalArgumentException("데이터가 존재하지 않습니다.");
        }

    }

}

package com.blaze.optimistic.domain.bank.account.repository;

import com.blaze.optimistic.domain.bank.account.adapter.BankAccountAdapter;
import com.blaze.optimistic.domain.bank.account.entity.BankAccount;
import jakarta.persistence.*;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountJpaRepository {
    @PersistenceContext
    private EntityManager em;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional
    public BankAccountAdapter save(BankAccount bankAccount) {

        if (bankAccount.getBankAccountId() == null) {
            em.persist(bankAccount);
        } else {
            em.merge(bankAccount);
        }

        return new BankAccountAdapter(bankAccount);
    }

    @Transactional
    public BankAccountAdapter findByBankAccountNumber(String bankAccountNumber) {
        String jpql = """
                SELECT ba\s
                FROM BankAccount ba\s
                WHERE ba.number = :bankAccountNumber
                """;

        TypedQuery<BankAccount> bankAccountTypedQuery = em.createQuery(jpql, BankAccount.class);
        bankAccountTypedQuery.setParameter("bankAccountNumber", bankAccountNumber);
//        bankAccountTypedQuery.setLockMode(LockModeType.OPTIMISTIC);

        BankAccount bankAccount;
        try {
            bankAccount = bankAccountTypedQuery.getSingleResult();
        } catch (OptimisticLockException optimisticLockException) {
            LOG.info("엔티티가 데이터베이스에서 수정 된 후, 다른 트랜잭션에서 같은 엔티티를 수정하려고 합니다.");
            throw new OptimisticLockException(optimisticLockException);
        } catch (ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException) {
            LOG.info("JPA 엔티티 버전 필드에 충돌을 감지 했습니다.");
            throw new ObjectOptimisticLockingFailureException("JPA 엔티티 버전 필드에 충돌을 감지 했습니다.", objectOptimisticLockingFailureException);
        } catch (StaleObjectStateException staleObjectStateException) {
            LOG.info("엔티티가 데이터베이스에서 로드된 이후에 다른 트랜잭션에서 변경을 했습니다.");
            throw new StaleObjectStateException(staleObjectStateException.getEntityName(), staleObjectStateException.getMessage());
        }

        return new BankAccountAdapter(bankAccount);
    }

}

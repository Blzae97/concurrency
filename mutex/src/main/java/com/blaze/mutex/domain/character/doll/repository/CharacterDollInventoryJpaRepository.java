package com.blaze.mutex.domain.character.doll.repository;

import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.domain.character.doll.entity.CharacterDollInventory;
import com.blaze.mutex.domain.character.doll.mapper.CharacterDollInventoryDomainMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

@Repository
public class CharacterDollInventoryJpaRepository {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager em;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional
    public <T> T upsert(CharacterDollInventoryDTO dto, Class<T> tClass) {
        CharacterDollInventory characterDollInventory = CharacterDollInventoryDomainMapper.toCharacterDollInventory(dto);

        Long characterDollInventoryId = characterDollInventory.getCharacterDollInventoryId();
        if (characterDollInventoryId == null) {
            return insert(characterDollInventory, tClass);
        } else {
            return update(dto, tClass);
        }

    }

    private <T> T insert(CharacterDollInventory characterDollInventory, Class<T> tClass) {
        em.persist(characterDollInventory);

        return createInstance(characterDollInventory, tClass);
    }

    private <T> T update(CharacterDollInventoryDTO dto, Class<T> tClass) {
        Long characterDollInventoryId = dto.getCharacterDollInventoryId();
        CharacterDollInventory characterDollInventory = findByCharacterDollInventoryId(characterDollInventoryId);
        characterDollInventory.allUpdate(
                dto.getCharacterDollInventoryId(),
                dto.getCharacterDollName(),
                dto.getCharacterDollCount()
        );

        em.merge(characterDollInventory);

        return createInstance(characterDollInventory, tClass);
    }


    public <T> T findByCharacterDollInventoryId(Long characterDollInventoryId, Class<T> tClass) {
        String jpql = """
                SELECT cdi\s
                FROM CharacterDollInventory cdi\s
                WHERE cdi.characterDollInventoryId = :characterDollInventoryId
                """;

        TypedQuery<T> tTypedQuery = em.createQuery(jpql, tClass);
        tTypedQuery.setParameter("characterDollInventoryId", characterDollInventoryId);

        try {
            return tTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("없는 인형 아이디 입니다.");
        }
    }

    private CharacterDollInventory findByCharacterDollInventoryId(Long characterDollInventoryId) {
        String jpql = """
                SELECT cdi\s
                FROM CharacterDollInventory cdi\s
                WHERE cdi.characterDollInventoryId = :characterDollInventoryId
                """;

        TypedQuery<CharacterDollInventory> characterDollInventoryTypedQuery = em.createQuery(jpql, CharacterDollInventory.class);
        characterDollInventoryTypedQuery.setParameter("characterDollInventoryId", characterDollInventoryId);

        try {
            return characterDollInventoryTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("없는 인형 아이디 입니다.");
        }
    }


    private <T> T createInstance(CharacterDollInventory characterDollInventory, Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor(CharacterDollInventory.class).newInstance(characterDollInventory);
        } catch (NoSuchMethodException e) {
            LOG.info("tClass에 대한 생성자를 찾을 수 없습니다. 엔티티를 받는 생성자가 없습니다.");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            LOG.info("생성자를 호출하는 도중에 생성자 내부에서 예외가 발생했습니다.");
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            LOG.info("인스턴스를 생성할 수 없습니다. tClass가 인터페이스이거나 추상 클래스입니다.");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            LOG.info("접근 제어를 위반 했습니다. 클래스나 생성자에 엑세스 권한이 없습니다.");
            throw new RuntimeException(e);
        }
    }

}

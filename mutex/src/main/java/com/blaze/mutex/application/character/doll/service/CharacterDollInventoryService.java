package com.blaze.mutex.application.character.doll.service;

import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.domain.character.doll.repository.CharacterDollInventoryJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterDollInventoryService {

    private final CharacterDollInventoryJpaRepository characterDollInventoryJpaRepository;

    public CharacterDollInventoryService(CharacterDollInventoryJpaRepository characterDollInventoryJpaRepository) {
        this.characterDollInventoryJpaRepository = characterDollInventoryJpaRepository;
    }

    @Transactional
    public CharacterDollInventoryDTO decreaseCharacterDoll(Long characterDollInventoryId) {
        CharacterDollInventoryDTO characterDollInventoryDTO
                = characterDollInventoryJpaRepository.findDTOByCharacterDollInventoryId(characterDollInventoryId);

        characterDollInventoryDTO.decreaseCharacterDollCount();

        CharacterDollInventoryDTO updatedCharacterDollInventoryDTO
                = characterDollInventoryJpaRepository.upsert(characterDollInventoryDTO, CharacterDollInventoryDTO.class);

        return updatedCharacterDollInventoryDTO;
    }
}

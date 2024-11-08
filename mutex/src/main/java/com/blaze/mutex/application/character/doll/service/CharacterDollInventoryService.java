package com.blaze.mutex.application.character.doll.service;

import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.domain.character.doll.repository.CharacterDollInventoryJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterDollInventoryService {
    private final CharacterDollInventoryJpaRepository characterDollInventoryJpaRepository;

    public CharacterDollInventoryService(CharacterDollInventoryJpaRepository characterDollInventoryJpaRepository) {
        this.characterDollInventoryJpaRepository = characterDollInventoryJpaRepository;
    }

    public CharacterDollInventoryDTO decreaseCharacterDoll(Long characterDollInventoryId) {
        CharacterDollInventoryDTO characterDollInventoryDTO
                = characterDollInventoryJpaRepository.findByCharacterDollInventoryId(characterDollInventoryId, CharacterDollInventoryDTO.class);

        characterDollInventoryDTO.decreaseCharacterDollCount();

        CharacterDollInventoryDTO updatedCharacterDollInventoryDTO
                = characterDollInventoryJpaRepository.upsert(characterDollInventoryDTO, CharacterDollInventoryDTO.class);

        return updatedCharacterDollInventoryDTO;
    }
}

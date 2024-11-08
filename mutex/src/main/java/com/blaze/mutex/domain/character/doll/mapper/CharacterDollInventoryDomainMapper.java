package com.blaze.mutex.domain.character.doll.mapper;

import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.domain.character.doll.entity.CharacterDollInventory;

public class CharacterDollInventoryDomainMapper {
    public static CharacterDollInventory toCharacterDollInventory(CharacterDollInventoryDTO c) {
        return CharacterDollInventory.builder()
                .characterDollInventoryId(c.getCharacterDollInventoryId())
                .characterDollName(c.getCharacterDollName())
                .characterDollCount(c.getCharacterDollCount())
                .build();
    }
}

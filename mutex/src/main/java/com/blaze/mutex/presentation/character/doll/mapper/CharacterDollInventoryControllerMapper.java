package com.blaze.mutex.presentation.character.doll.mapper;

import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.presentation.character.doll.dto.CharacterDollInventoryResponse;

public class CharacterDollInventoryControllerMapper {
    public static CharacterDollInventoryResponse toCharacterDollInventoryResponse(CharacterDollInventoryDTO dto) {
        return CharacterDollInventoryResponse.builder()
                .characterDollInventoryId(dto.getCharacterDollInventoryId())
                .characterDollName(dto.getCharacterDollName())
                .characterDollCount(dto.getCharacterDollCount())
                .build();
    }
}

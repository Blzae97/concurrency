package com.blaze.mutex.presentation.character.doll.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CharacterDollInventoryResponse {
    private final Long characterDollInventoryId;
    private final String characterDollName;
    private final Long characterDollCount;

    @Builder
    public CharacterDollInventoryResponse(Long characterDollInventoryId, String characterDollName, Long characterDollCount) {
        this.characterDollInventoryId = characterDollInventoryId;
        this.characterDollName = characterDollName;
        this.characterDollCount = characterDollCount;
    }
}

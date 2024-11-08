package com.blaze.mutex.domain.character.doll.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CharacterDollInventoryDTO {
    private Long characterDollInventoryId;
    private String characterDollName;
    private Long characterDollCount;

    @Builder
    public CharacterDollInventoryDTO(Long characterDollInventoryId, String characterDollName, Long characterDollCount) {
        this.characterDollInventoryId = characterDollInventoryId;
        this.characterDollName = characterDollName;
        this.characterDollCount = characterDollCount;
    }
}

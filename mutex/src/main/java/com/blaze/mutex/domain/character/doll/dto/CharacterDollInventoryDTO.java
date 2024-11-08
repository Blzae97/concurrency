package com.blaze.mutex.domain.character.doll.dto;

import com.blaze.mutex.domain.character.doll.entity.CharacterDollInventory;
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

    public CharacterDollInventoryDTO(CharacterDollInventory characterDollInventory) {
        this.characterDollInventoryId = characterDollInventory.getCharacterDollInventoryId();
        this.characterDollName = characterDollInventory.getCharacterDollName();
        this.characterDollCount = characterDollInventory.getCharacterDollCount();
    }

    public void decreaseCharacterDollCount() {
        this.characterDollCount -= 1;
    }
}

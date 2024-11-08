package com.blaze.mutex.presentation.character.doll.controller;

import com.blaze.mutex.application.character.doll.service.CharacterDollInventoryService;
import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.presentation.character.doll.dto.CharacterDollInventoryResponse;
import com.blaze.mutex.presentation.character.doll.mapper.CharacterDollInventoryControllerMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/character/doll")
public class CharacterDollInventoryRestController {

    private final CharacterDollInventoryService characterDollInventoryService;

    public CharacterDollInventoryRestController(CharacterDollInventoryService characterDollInventoryService) {
        this.characterDollInventoryService = characterDollInventoryService;
    }

    @PostMapping(value = "/decrease")
    public CharacterDollInventoryResponse decreaseCharacterDoll(
            @RequestParam(value = "characterDollInventoryId") Long characterDollInventoryId
    ) {
        CharacterDollInventoryDTO characterDollInventoryDTO
                = characterDollInventoryService.decreaseCharacterDoll(characterDollInventoryId);

        CharacterDollInventoryResponse characterDollInventoryResponse
                = CharacterDollInventoryControllerMapper.toCharacterDollInventoryResponse(characterDollInventoryDTO);

        return characterDollInventoryResponse;
    }
}

package com.blaze.mutex.presentation.character.doll.controller;

import com.blaze.mutex.application.character.doll.service.CharacterDollInventoryService;
import com.blaze.mutex.domain.character.doll.dto.CharacterDollInventoryDTO;
import com.blaze.mutex.presentation.character.doll.dto.CharacterDollInventoryResponse;
import com.blaze.mutex.presentation.character.doll.mapper.CharacterDollInventoryControllerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping(value = "/api/character/doll")
public class CharacterDollInventoryRestController {

    private final CharacterDollInventoryService characterDollInventoryService;


    private final Lock lock = new ReentrantLock();
    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public CharacterDollInventoryRestController(CharacterDollInventoryService characterDollInventoryService) {
        this.characterDollInventoryService = characterDollInventoryService;
    }

    @PostMapping(value = "/decrease")
    public CharacterDollInventoryResponse decreaseCharacterDoll(
            @RequestParam(value = "characterDollInventoryId") Long characterDollInventoryId
    ) {
        LOG.info("Thread {} 리소스에 접근을 시도!", Thread.currentThread().getId());

        lock.lock();
        LOG.info("Thread {} 리소스에 사용 진입!", Thread.currentThread().getId());
        CharacterDollInventoryDTO characterDollInventoryDTO
                = characterDollInventoryService.decreaseCharacterDoll(characterDollInventoryId);
        LOG.info("Thread {} 리소스에 사용 종료!", Thread.currentThread().getId());
        lock.unlock();

        LOG.info("Thread {} 리소스에 접근 해제!", Thread.currentThread().getId());

        CharacterDollInventoryResponse characterDollInventoryResponse
                = CharacterDollInventoryControllerMapper.toCharacterDollInventoryResponse(characterDollInventoryDTO);

        LOG.info("{}", characterDollInventoryResponse);

        return characterDollInventoryResponse;
    }
}

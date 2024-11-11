package com.blaze.semaphore.presentation.controller;

import com.blaze.semaphore.application.docs.service.DocsService;
import com.blaze.semaphore.domain.docs.dto.DocsDTO;
import com.blaze.semaphore.presentation.dto.DocsFetchResponse;
import com.blaze.semaphore.presentation.mapper.DocsFetchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/docs")
public class DocsRestController {

    private final DocsService docsService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public DocsRestController(DocsService docsService) {
        this.docsService = docsService;
    }

    @GetMapping(value = "/detail/{docsId}")
    public DocsFetchResponse fetchDocsDetail(@PathVariable(value = "docsId") Long docsId) {

        LOG.info("Thread {} 리소스에 접근을 시도!", Thread.currentThread().getId());
        DocsDTO docsDTO = docsService.fetchDocs(docsId);
        LOG.info("Thread {} 리소스에 접근 종료!", Thread.currentThread().getId());

        DocsFetchMapper docsFetchMapper = new DocsFetchMapper();

        return docsFetchMapper.toFetchResponse(docsDTO);
    }

}

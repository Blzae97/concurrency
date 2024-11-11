package com.blaze.semaphore.application.docs.service;

import com.blaze.semaphore.application.docs.mapper.DocsMapper;
import com.blaze.semaphore.domain.docs.adapter.DocsAdapter;
import com.blaze.semaphore.domain.docs.dto.DocsDTO;
import com.blaze.semaphore.domain.docs.repository.DocsJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

@Service
public class DocsService {
    private final DocsJpaRepository docsJpaRepository;

    private final Semaphore semaphore = new Semaphore(1);

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public DocsService(DocsJpaRepository docsJpaRepository) {
        this.docsJpaRepository = docsJpaRepository;
    }

    public DocsDTO fetchDocs(Long docsId) {

        LOG.info("Thread {} 리소스에 접근 대기!", Thread.currentThread().getId());

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOG.info("Thread {} 리소스에 접근!", Thread.currentThread().getId());

        // 게시글 조회
        DocsAdapter docsAdapter = docsJpaRepository.findByDocsId(docsId);

        // 뷰 카운트 증가
        docsAdapter.viewCountIncrease();
        DocsAdapter savedDocsAdapter = docsJpaRepository.save(docsAdapter.getDocs());

        LOG.info("[{}] {}(vc:{}) 게시글을 조회 했습니다.", Thread.currentThread().getId(), docsAdapter.getTitle(), docsAdapter.getViewCount());

        semaphore.release();

        DocsMapper docsMapper = new DocsMapper();
        return docsMapper.toDTO(savedDocsAdapter.getDocs());
    }
}

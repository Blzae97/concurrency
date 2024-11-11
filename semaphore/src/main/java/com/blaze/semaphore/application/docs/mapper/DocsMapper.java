package com.blaze.semaphore.application.docs.mapper;

import com.blaze.semaphore.domain.docs.dto.DocsDTO;
import com.blaze.semaphore.domain.docs.entity.Docs;

public class DocsMapper implements Mapper<Docs, DocsDTO> {
    @Override
    public DocsDTO toDTO(Docs entity) {
        return DocsDTO.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .build();
    }

    @Override
    public Docs toEntity(DocsDTO dto) {
        return Docs.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .viewCount(0L)
                .build();
    }
}

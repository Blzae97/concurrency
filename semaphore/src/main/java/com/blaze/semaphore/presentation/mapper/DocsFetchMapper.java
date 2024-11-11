package com.blaze.semaphore.presentation.mapper;

import com.blaze.semaphore.domain.docs.dto.DocsDTO;
import com.blaze.semaphore.presentation.dto.DocsFetchResponse;

public class DocsFetchMapper implements Mapper<DocsDTO, DocsFetchResponse> {
    @Override
    public DocsFetchResponse toFetchResponse(DocsDTO dto) {
        return DocsFetchResponse.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .viewCount(dto.getViewCount())
                .build();
    }
}

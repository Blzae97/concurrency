package com.blaze.semaphore.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DocsFetchResponse {
    private final String title;
    private final String content;
    private final Long viewCount;

    @Builder
    public DocsFetchResponse(String title, String content, Long viewCount) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}

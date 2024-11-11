package com.blaze.semaphore.domain.docs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DocsDTO {
    private String title;
    private String content;
    private Long viewCount;

    @Builder
    public DocsDTO(String title, String content, Long viewCount) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}
